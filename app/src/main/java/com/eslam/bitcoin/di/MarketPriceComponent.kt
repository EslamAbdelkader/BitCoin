package com.eslam.bitcoin.di

import com.eslam.bitcoin.domain.MarketPriceInteractor
import com.eslam.bitcoin.repository.MarketPriceRepository
import com.eslam.bitcoin.util.StringProvider
import com.eslam.bitcoin.viewmodel.MarketPriceViewModel
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