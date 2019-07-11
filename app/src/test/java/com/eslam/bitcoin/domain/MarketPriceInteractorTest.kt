package com.eslam.bitcoin.domain

import com.eslam.bitcoin.model.MarketPriceResponse
import com.eslam.bitcoin.repository.IMarketPriceRepository
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
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
    fun setUp() {
        val repo = mock(IMarketPriceRepository::class.java)
        whenever(repo.getMarketPrice()).thenReturn(dummyResponse())

        interactor.marketPriceRepository = repo
    }

    /**
     * Passing Single observable with the constant response
     */
    private fun dummyResponse(): Single<MarketPriceResponse>? {
        return Single.create { it.onSuccess(MARKET_PRICE_RESPONSE) }
    }

    /**
     * Testing that the interactor is just passing the values from the Repository without any manipulation.
     */
    @Test
    fun testInteractorPassingResponseWithoutManipulation() {
        var response: MarketPriceResponse? = null
        interactor.loadData().subscribeBy { response = it }
        assertNotNull(response)
        assertEquals(response, MARKET_PRICE_RESPONSE)
    }
}