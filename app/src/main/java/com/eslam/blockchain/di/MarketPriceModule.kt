package com.eslam.blockchain.di

import android.content.Context
import androidx.arch.core.util.Function
import com.eslam.blockchain.BlockChainApplication
import com.eslam.blockchain.domain.IMarketPriceInteractor
import com.eslam.blockchain.model.MarketPriceMapper
import com.eslam.blockchain.model.MarketPriceResponse
import com.eslam.blockchain.model.MarketPriceUIModel
import com.eslam.blockchain.network.ChartApi
import com.eslam.blockchain.network.retrofit
import com.eslam.blockchain.repository.IMarketPriceRepository
import com.eslam.blockchain.util.IStringProvider
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
    fun provideContext(): Context = BlockChainApplication.context
}