package com.eslam.bitcoin.model

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Dummy constant data
 */
private const val NAME = "name"
private const val DESCRIPTION = "description"
private val VALUE_1 by lazy { Value(1, 1f) }
private val VALUE_2 by lazy { Value(2, 2f) }
private val VALUE_3 by lazy { Value(3, 3f) }
private val VALUES by lazy {
    mutableListOf<Value>().apply {
        add(VALUE_1)
        add(VALUE_2)
        add(VALUE_3)
    }
}

private val MARKET_PRICE_RESPONSE by lazy {
    MarketPriceResponse(NAME, DESCRIPTION, VALUES)
}

/**
 * Unit test for MarketPriceMapper
 */
class MarketPriceMapperTest {

    /**
     * Testing mapping MarketPriceResponse to MarketPriceUIModel
     */
    @Test
    fun testMapping() {
        val mapper = MarketPriceMapper()
        val uiModel = mapper.apply(MARKET_PRICE_RESPONSE)

        assertEquals(uiModel.title, NAME)
        assertEquals(uiModel.subtitle, DESCRIPTION)

        assert(!uiModel.graphPoints.isNullOrEmpty())
        assertEquals(uiModel.graphPoints.size, 3)

        assertEquals(uiModel.graphPoints[0].x, VALUE_1.time.toFloat())
        assertEquals(uiModel.graphPoints[0].y, VALUE_1.currencyValue)
        assertEquals(uiModel.graphPoints[1].x, VALUE_2.time.toFloat())
        assertEquals(uiModel.graphPoints[1].y, VALUE_2.currencyValue)
        assertEquals(uiModel.graphPoints[2].x, VALUE_3.time.toFloat())
        assertEquals(uiModel.graphPoints[2].y, VALUE_3.currencyValue)
    }
}