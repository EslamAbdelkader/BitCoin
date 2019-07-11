package com.eslam.bitcoin.repository

import com.eslam.bitcoin.model.MarketPriceResponse
import com.eslam.bitcoin.network.ChartApi
import com.nhaarman.mockitokotlin2.whenever
import io.github.plastix.rxschedulerrule.RxSchedulerRule
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

/**
 * Dummy constant response
 */
private val MARKET_PRICE_RESPONSE = MarketPriceResponse()

/**
 * Unit test for MarketPriceRepository
 */
class MarketPriceRepositoryTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    private val repo: MarketPriceRepository by lazy { MarketPriceRepository() }

    /**
     * Mocking the network data source to initialize the Repo
     */
    @Before
    fun setUp() {
        val chartApi = Mockito.mock(ChartApi::class.java)
        whenever(chartApi.getMarketPrice()).thenReturn(dummyResponse())
        repo.chartApi = chartApi
    }

    /**
     * Passing Single observable with the constant response
     */
    private fun dummyResponse(): Single<MarketPriceResponse>? {
        return Single.create { it.onSuccess(MARKET_PRICE_RESPONSE) }
    }

    /**
     * Testing that the Repo is just passing the values from the Network data source without any manipulation.
     * The test relies on the fact that all response's
     */
    @Test
    fun testRepoPassingDataWithoutManipulation() {
        var response: MarketPriceResponse? = null
        repo.getMarketPrice().subscribeBy { response = it }
        assertNotNull(response)
        assertEquals(response, MARKET_PRICE_RESPONSE)
    }
}