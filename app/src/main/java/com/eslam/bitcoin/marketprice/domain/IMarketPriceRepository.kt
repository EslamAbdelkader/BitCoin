package com.eslam.bitcoin.marketprice.domain

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse

/**
 * Market Price Repository Interface
 */
interface IMarketPriceRepository {
    /**
     * Fetches market price values from network data source ([chartApi])
     */
    @Throws(Exception::class)
    suspend fun getMarketPrice(): MarketPriceResponse
}