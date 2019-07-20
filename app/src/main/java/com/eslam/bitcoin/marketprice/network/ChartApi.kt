package com.eslam.bitcoin.marketprice.network

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
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