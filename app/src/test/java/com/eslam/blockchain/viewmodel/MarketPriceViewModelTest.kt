package com.eslam.blockchain.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.arch.core.util.Function
import androidx.lifecycle.viewModelScope
import com.eslam.blockchain.R
import com.eslam.blockchain.domain.IMarketPriceInteractor
import com.eslam.blockchain.model.MarketPriceMapper
import com.eslam.blockchain.model.MarketPriceResponse
import com.eslam.blockchain.model.MarketPriceUIModel
import com.eslam.blockchain.model.State
import com.eslam.blockchain.util.IStringProvider
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.lang.Runnable
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import java.util.concurrent.Executor

private const val GENERAL_ERROR_MESSAGE = "general error message"
private const val NETWORK_ERROR_MESSAGE = "network error message"

/**
 * Unit test for [MarketPriceViewModel]
 */
@ExperimentalCoroutinesApi
class MarketPriceViewModelTest {
    /**
     * For LiveData Instant Execution
     */
    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val mapper: Function<MarketPriceResponse, MarketPriceUIModel> by lazy { Mockito.mock(MarketPriceMapper::class.java) }
    private val interactor: IMarketPriceInteractor by lazy { Mockito.mock(IMarketPriceInteractor::class.java) }
    private val stringProvider: IStringProvider by lazy { Mockito.mock(IStringProvider::class.java) }
    private val viewModel: MarketPriceViewModel by lazy {
        MarketPriceViewModel().apply {
            mapper = this@MarketPriceViewModelTest.mapper
            interactor = this@MarketPriceViewModelTest.interactor
            stringProvider = this@MarketPriceViewModelTest.stringProvider
        }
    }

    /**
     * Initialize with mocks
     */
    @Before
    fun setUp() {
        whenever(stringProvider.getString(R.string.general_error_message)).thenReturn(GENERAL_ERROR_MESSAGE)
        whenever(stringProvider.getString(R.string.network_error_message)).thenReturn(NETWORK_ERROR_MESSAGE)
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    /**
     * Test status [State.LOADING] is returned on subscribe
     */
    @Test
    fun testLoadingStatusWhileFetchingData() = runBlocking {
        Dispatchers.setMain(IgnoringExecutor().asCoroutineDispatcher())
        whenever(interactor.loadData()).thenReturn(MarketPriceResponse())

        assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assertEquals(viewModel.getState().value, State.LOADING)
    }

    /**
     * Test status [State.SUCCESS] is returned on success with model in data
     */
    @Test
    fun testSuccessStatusOnSuccess() = runBlocking {
        val response = MarketPriceResponse()
        val model = MarketPriceUIModel()

        whenever(interactor.loadData()).thenReturn(response)
        whenever(mapper.apply(response)).thenReturn(model)

        assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assert(viewModel.getState().value is State.SUCCESS<*>)
    }

    /**
     * Test status [State.ERROR] is returned on error with message
     */
    @Test
    fun testErrorStatusOnError() = runBlocking {
        whenever(interactor.loadData()).thenThrow(Error())

        assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assert(viewModel.getState().value is State.ERROR)
    }

    /**
     * Test network error message is returned when a connection error is thrown
     */
    @Test
    fun testNetworkErrorMessageOnNetworkError() = runBlocking {
        for (error in listOf(ConnectException(), SocketException(), UnknownHostException())) {

            doThrow(error).whenever(interactor).loadData()

            viewModel.loadData()

            assert(viewModel.getState().value is State.ERROR)

            assertEquals((viewModel.getState().value as State.ERROR).message, NETWORK_ERROR_MESSAGE)
        }
    }

    /**
     * Test general error message is returned when a not-connection-related error is thrown
     */
    @Test
    fun testGeneralErrorMessageOnOtherErrors() = runBlocking(viewModel.viewModelScope.coroutineContext) {
        whenever(interactor.loadData()).thenThrow(RuntimeException())

        viewModel.loadData()

        assert(viewModel.getState().value is State.ERROR)

        assertEquals((viewModel.getState().value as State.ERROR).message, GENERAL_ERROR_MESSAGE)
    }

    /**
     * Test view model uses [mapper] to convert values on success
     */
    @Test
    fun testMapperMethodIsCalledOnSuccess() = runBlocking<Unit> {
        whenever(interactor.loadData()).thenReturn(MarketPriceResponse())

        viewModel.loadData()

        verify(mapper, times(1)).apply(any())
    }
}

class IgnoringExecutor : Executor {
    override fun execute(command: Runnable?) {
        // Never Execute
    }
}