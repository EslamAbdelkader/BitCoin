package com.eslam.blockchain.di

import com.eslam.blockchain.viewmodel.MarketPriceViewModel
import com.eslam.blockchain.domain.MarketPriceInteractor
import com.eslam.blockchain.repository.MarketPriceRepository
import com.eslam.blockchain.util.StringProvider
import dagger.Component

@Component(modules = [MarketPriceModule::class])
interface MarketPriceComponent {
    fun inject(viewModel: MarketPriceViewModel)
    fun mainInteractor(): MarketPriceInteractor
    fun mainRepository(): MarketPriceRepository
    fun stringProvider(): StringProvider
}