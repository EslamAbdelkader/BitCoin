package com.eslam.bitcoin.marketprice.presentation

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse

/**
 * Market Price Interactor Interface
 */
interface IMarketPriceInteractor {
    /**
     * retrieves a Single Observable of MarketPriceResponse from the Repository
     */
    @Throws(Exception::class)
    suspend fun loadData(): MarketPriceResponse
}