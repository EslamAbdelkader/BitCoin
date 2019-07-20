package com.eslam.bitcoin.marketprice.di

import android.content.Context
import androidx.arch.core.util.Function
import com.eslam.bitcoin.marketprice.domain.IMarketPriceInteractor
import com.eslam.bitcoin.marketprice.domain.MarketPriceInteractor
import com.eslam.bitcoin.marketprice.model.MarketPriceMapper
import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import com.eslam.bitcoin.marketprice.model.MarketPriceUIModel
import com.eslam.bitcoin.marketprice.network.ChartApi
import com.eslam.bitcoin.marketprice.network.retrofit
import com.eslam.bitcoin.marketprice.repository.IMarketPriceRepository
import com.eslam.bitcoin.marketprice.repository.MarketPriceRepository
import com.eslam.bitcoin.util.IStringProvider
import com.eslam.bitcoin.util.StringProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MarketPriceModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideChartApi(): ChartApi = retrofit.create(ChartApi::class.java)

    @Provides
    @Singleton
    fun provideMainRepository(marketPriceRepository: MarketPriceRepository): IMarketPriceRepository {
        return marketPriceRepository
    }

    @Provides
    @Singleton
    fun provideMainInteractor(marketPriceInteractor: MarketPriceInteractor): IMarketPriceInteractor {
        return marketPriceInteractor
    }

    @Provides
    @Singleton
    fun provideMapper(marketPriceMapper: MarketPriceMapper): Function<MarketPriceResponse, MarketPriceUIModel> {
        return marketPriceMapper
    }

    @Provides
    @Singleton
    fun provideStringProvider(stringProvider: StringProvider): IStringProvider {
        return stringProvider
    }

    @Provides
    @Singleton
    fun provideContext(): Context {
        return context
    }
}