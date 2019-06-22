package com.eslam.blockchain.network

import com.eslam.blockchain.model.MarketPriceResponse
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Network data source for charts api
 */
interface ChartApi {
    /**
     * Retrieves the list of market price values from the charts api
     */
    @GET("charts/market-price")
    fun getMarketPrice(): Single<MarketPriceResponse>
}