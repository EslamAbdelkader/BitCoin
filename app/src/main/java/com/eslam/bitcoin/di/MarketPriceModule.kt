package com.eslam.bitcoin.di

import android.content.Context
import androidx.arch.core.util.Function
import com.eslam.bitcoin.BitCoinApplication
import com.eslam.bitcoin.domain.IMarketPriceInteractor
import com.eslam.bitcoin.model.MarketPriceMapper
import com.eslam.bitcoin.model.MarketPriceResponse
import com.eslam.bitcoin.model.MarketPriceUIModel
import com.eslam.bitcoin.network.ChartApi
import com.eslam.bitcoin.network.retrofit
import com.eslam.bitcoin.repository.IMarketPriceRepository
import com.eslam.bitcoin.util.IStringProvider
import dagger.Module
import dagger.Provides

@Module
class MarketPriceModule {
    @Provides
    fun provideChartApi(): ChartApi = retrofit.create(ChartApi::class.java)

    @Provides
    fun provideMainRepository(): IMarketPriceRepository = DaggerMarketPriceComponent.create().mainRepository()

    @Provides
    fun provideMainInteractor(): IMarketPriceInteractor = DaggerMarketPriceComponent.create().mainInteractor()

    @Provides
    fun provideMapper(): Function<MarketPriceResponse, MarketPriceUIModel> = MarketPriceMapper()

    @Provides
    fun provideStringProvider(): IStringProvider = DaggerMarketPriceComponent.create().stringProvider()

    @Provides
    fun provideContext(): Context = BitCoinApplication.context
}