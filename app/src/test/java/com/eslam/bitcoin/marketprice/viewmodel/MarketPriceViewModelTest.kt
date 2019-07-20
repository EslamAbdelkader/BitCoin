package com.eslam.bitcoin.marketprice.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.arch.core.util.Function
import com.eslam.bitcoin.R
import com.eslam.bitcoin.marketprice.domain.IMarketPriceInteractor
import com.eslam.bitcoin.marketprice.model.MarketPriceMapper
import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import com.eslam.bitcoin.marketprice.model.MarketPriceUIModel
import com.eslam.bitcoin.marketprice.model.State
import com.eslam.bitcoin.util.IStringProvider
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException

private const val GENERAL_ERROR_MESSAGE = "general error message"
private const val NETWORK_ERROR_MESSAGE = "network error message"

/**
 * Unit test for [MarketPriceViewModel]
 */
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
    }

    /**
     * Test status [State.LOADING] is returned on subscribe
     */
    @Test
    fun testLoadingStatusOnSubscribe() {
        whenever(interactor.loadData()).thenReturn(Single.never())

        assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assertEquals(viewModel.getState().value, State.LOADING)
    }

    /**
     * Test status [State.SUCCESS] is returned on success with model in data
     */
    @Test
    fun testSuccessStatusOnSuccess() {
        val response = MarketPriceResponse()
        val model = MarketPriceUIModel()

        whenever(interactor.loadData()).thenReturn(Single.just(response))
        whenever(mapper.apply(response)).thenReturn(model)

        assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assert(viewModel.getState().value is State.SUCCESS<*>)
    }

    /**
     * Test status [State.ERROR] is returned on error with message
     */
    @Test
    fun testErrorStatusOnError() {
        whenever(interactor.loadData()).thenReturn(Single.error(Error()))

        assertEquals(viewModel.getState().value, null)

        viewModel.loadData()

        assert(viewModel.getState().value is State.ERROR)
    }

    /**
     * Test network error message is returned when a connection error is thrown
     */
    @Test
    fun testNetworkErrorMessageOnNetworkError() {
        for (error in listOf(ConnectException(), SocketException(), UnknownHostException())) {

            whenever(interactor.loadData()).thenReturn(Single.error(error))

            viewModel.loadData()

            assert(viewModel.getState().value is State.ERROR)

            assertEquals((viewModel.getState().value as State.ERROR).message, NETWORK_ERROR_MESSAGE)
        }
    }

    /**
     * Test general error message is returned when a not-connection-related error is thrown
     */
    @Test
    fun testGeneralErrorMessageOnOtherErrors() {
        whenever(interactor.loadData()).thenReturn(Single.error(Exception()))

        viewModel.loadData()

        assert(viewModel.getState().value is State.ERROR)

        assertEquals((viewModel.getState().value as State.ERROR).message, GENERAL_ERROR_MESSAGE)
    }

    /**
     * Test view model uses [mapper] to convert values on success
     */
    @Test
    fun testMapperMethodIsCalledOnSuccess() {
        whenever(interactor.loadData()).thenReturn(Single.just(MarketPriceResponse()))

        viewModel.loadData()

        verify(mapper, times(1)).apply(any())
    }
}