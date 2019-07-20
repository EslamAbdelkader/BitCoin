package com.eslam.bitcoin.marketprice.repository

import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import com.eslam.bitcoin.marketprice.network.ChartApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Responsible for fetching the data from data sources,
 * so far it only fetches from network data source, but it can also be responsible
 * for fetching from local data source, and synchronization between both sources
 */
class MarketPriceRepository @Inject constructor() : IMarketPriceRepository {

    @Inject
    lateinit var chartApi: ChartApi

    /**
     * Fetches market price values from network data source ([chartApi])
     */
    override fun getMarketPrice(): Single<MarketPriceResponse> {
        return chartApi
            .getMarketPrice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * Market Price Repository Interface
 */
interface IMarketPriceRepository {
    fun getMarketPrice(): Single<MarketPriceResponse>
}