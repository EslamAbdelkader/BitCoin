package com.eslam.bitcoin.repository

import com.eslam.bitcoin.model.MarketPriceResponse
import com.eslam.bitcoin.network.ChartApi
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
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

    private val repo: MarketPriceRepository by lazy { MarketPriceRepository() }

    /**
     * Mocking the network data source to initialize the Repo
     */
    @Before
    fun setUp() = runBlocking {
        val chartApi = Mockito.mock(ChartApi::class.java)
        whenever(chartApi.getMarketPrice()).thenReturn(MARKET_PRICE_RESPONSE)
        repo.chartApi = chartApi
    }

    /**
     * Testing that the Repo is just passing the values from the Network data source without any manipulation.
     * The test relies on the fact that all response's
     */
    @Test
    fun testRepoPassingDataWithoutManipulation() = runBlocking {
        val response: MarketPriceResponse? = repo.getMarketPrice()
        assertNotNull(response)
        assertEquals(response, MARKET_PRICE_RESPONSE)
    }
}