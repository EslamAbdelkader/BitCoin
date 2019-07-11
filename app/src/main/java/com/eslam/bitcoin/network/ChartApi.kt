package com.eslam.bitcoin.network

import com.eslam.bitcoin.model.MarketPriceResponse
import retrofit2.http.GET

/**
 * Network data source for charts api
 */
interface ChartApi {
    /**
     * Retrieves the list of market price values from the charts api
     */
    @GET("charts/market-price")
    suspend fun getMarketPrice(): MarketPriceResponse
}