package com.eslam.bitcoin.marketprice.presentation

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import io.reactivex.Single

/**
 * Market Price Interactor Interface
 */
interface IMarketPriceInteractor {
    /**
     * retrieves a Single Observable of MarketPriceResponse from the Repository
     */
    @Throws(Exception::class)
    fun loadData(): Single<MarketPriceResponse>
}