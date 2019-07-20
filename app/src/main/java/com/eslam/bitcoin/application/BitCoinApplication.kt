package com.eslam.bitcoin.application

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.eslam.bitcoin.marketprice.di.DaggerMarketPriceComponent
import com.eslam.bitcoin.marketprice.di.MarketPriceComponent
import com.eslam.bitcoin.marketprice.di.MarketPriceModule

/**
 * Application class
 */
class BitCoinApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
        component = DaggerMarketPriceComponent.builder()
            .marketPriceModule(MarketPriceModule(context))
            .build()
    }

    companion object {
        /**
         * Not leaking, keeping the application context static, to provide for Dependency Injection
         */
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        lateinit var component: MarketPriceComponent
    }
}