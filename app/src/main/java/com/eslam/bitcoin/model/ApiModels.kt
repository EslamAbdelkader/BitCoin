package com.eslam.bitcoin.model

import com.google.gson.annotations.SerializedName

/**
 * The MarketPrice Api Model that is passed between the domain and the data layers
 */
data class MarketPriceResponse(
    @SerializedName("name") val name: String? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("values") val values: List<Value>? = null
)

/**
 * A wrapper class for the bitcoin market price value at a certain time
 */
data class Value(@SerializedName("x") val time: Long, @SerializedName("y") val currencyValue: Float)
