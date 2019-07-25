package com.eslam.bitcoin.marketprice.di

import com.eslam.bitcoin.marketprice.presentation.MarketPriceViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [MarketPriceModule::class])
interface MarketPriceComponent {
    fun inject(viewModel: MarketPriceViewModel)
}