package com.eslam.bitcoin.model

import androidx.arch.core.util.Function
import com.github.mikephil.charting.data.Entry

/**
 * A mapper class responsible for mapping [MarketPriceResponse] into [MarketPriceUIModel]
 */
open class MarketPriceMapper : Function<MarketPriceResponse, MarketPriceUIModel> {

    /**
     * mapping [response] of type [MarketPriceResponse] to [MarketPriceUIModel]
     */
    override fun apply(response: MarketPriceResponse): MarketPriceUIModel {

        val graphPoints = response.values?.map { Entry(it.time.toFloat(), it.currencyValue) } ?: emptyList()

        return MarketPriceUIModel(response.name, response.description, graphPoints)
    }

}