package com.eslam.bitcoin.marketprice.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eslam.bitcoin.application.BitCoinApplication

/**
 * Factory class for [MarketPriceViewModel]
 */
@Suppress("UNCHECKED_CAST")
class MarketPriceViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val viewModel = MarketPriceViewModel()
        BitCoinApplication.component.inject(viewModel)
        viewModel.loadData()
        return viewModel as T
    }
}