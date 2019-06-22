package com.eslam.blockchain.domain

import com.eslam.blockchain.model.MarketPriceResponse
import com.eslam.blockchain.repository.IMarketPriceRepository
import io.reactivex.Single
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
    override fun loadData(): Single<MarketPriceResponse> = marketPriceRepository.getMarketPrice()
}

/**
 * Market Price Interactor Interface
 */
interface IMarketPriceInteractor {
    /**
     * retrieves a Single Observable of MarketPriceResponse from the Repository
     */
    fun loadData(): Single<MarketPriceResponse>
}