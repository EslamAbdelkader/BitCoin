package com.eslam.bitcoin.domain

import com.eslam.bitcoin.model.MarketPriceResponse
import com.eslam.bitcoin.repository.IMarketPriceRepository
import javax.inject.Inject

/**
 * Market Price Interactor Implementation.
 * Doesn't actually do much for now, but should be responsible for handling any business logic,
 * like sorting or filtering the data, before sending them to the ViewModel
 */
class MarketPriceInteractor @Inject constructor() : IMarketPriceInteractor {
    @Inject
    lateinit var marketPriceRepository: IMarketPriceRepository

    /**
     * retrieves a Single Observable of MarketPriceResponse from the Repository
     */
    @Throws(Exception::class)
    override suspend fun loadData(): MarketPriceResponse = marketPriceRepository.getMarketPrice()
}

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