package com.eslam.bitcoin.marketprice.di

import com.eslam.bitcoin.marketprice.domain.MarketPriceInteractor
import com.eslam.bitcoin.marketprice.repository.MarketPriceRepository
import com.eslam.bitcoin.util.StringProvider
import com.eslam.bitcoin.marketprice.viewmodel.MarketPriceViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MarketPriceModule::class])
interface MarketPriceComponent {
    fun inject(viewModel: MarketPriceViewModel)
    fun mainInteractor(): MarketPriceInteractor
    fun mainRepository(): MarketPriceRepository
    fun stringProvider(): StringProvider
}