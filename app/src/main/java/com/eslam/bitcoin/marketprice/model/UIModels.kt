package com.eslam.bitcoin.marketprice.model

import com.github.mikephil.charting.data.Entry

/**
 * The MarketPrice UI Model that is passed between ViewModel and View
 */
data class MarketPriceUIModel(
    val title: String? = null,
    val subtitle: String? = null,
    val graphPoints: List<Entry> = emptyList()
)

/**
 * A model representing the state of the data (LOADING, SUCCESS with data, or ERROR with message)
 */
sealed class State {

    object LOADING : State()

    data class SUCCESS<T>(val data: T) : State()

    data class ERROR(val message: String) : State()
}