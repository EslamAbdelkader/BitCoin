package com.eslam.bitcoin.marketprice.repository

import com.eslam.bitcoin.marketprice.domain.IMarketPriceRepository
import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import com.eslam.bitcoin.marketprice.network.ChartApi
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
    @Throws(Exception::class)
    override suspend fun getMarketPrice(): MarketPriceResponse = chartApi.getMarketPrice()
}