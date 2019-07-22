package com.eslam.bitcoin.marketprice.domain

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import io.reactivex.Single

/**
 * Market Price Repository Interface
 */
interface IMarketPriceRepository {
    fun getMarketPrice(): Single<MarketPriceResponse>
}