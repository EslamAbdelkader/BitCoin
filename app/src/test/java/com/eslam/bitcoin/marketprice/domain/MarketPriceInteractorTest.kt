package com.eslam.bitcoin.marketprice.domain

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock

/**
 * Dummy constant response
 */
private val MARKET_PRICE_RESPONSE by lazy { MarketPriceResponse() }

/**
 * Unit test for MarketPriceInteractor
 */
class MarketPriceInteractorTest {

    private val interactor: MarketPriceInteractor by lazy { MarketPriceInteractor() }

    /**
     * Mocking the Repo to initialize the interactor
     */
    @Before
    fun setUp() = runBlocking {
        val repo = mock(IMarketPriceRepository::class.java)
        whenever(repo.getMarketPrice()).thenReturn(MARKET_PRICE_RESPONSE)

        interactor.marketPriceRepository = repo
    }

    /**
     * Testing that the interactor is just passing the values from the Repository without any manipulation.
     */
    @Test
    fun testInteractorPassingResponseWithoutManipulation() = runBlocking {
        val response: MarketPriceResponse? = interactor.loadData()
        assertNotNull(response)
        assertEquals(response, MARKET_PRICE_RESPONSE)
    }
}