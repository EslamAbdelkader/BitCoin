package com.eslam.bitcoin.marketprice.viewmodel

import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eslam.bitcoin.R
import com.eslam.bitcoin.marketprice.domain.IMarketPriceInteractor
import com.eslam.bitcoin.marketprice.model.MarketPriceResponse
import com.eslam.bitcoin.marketprice.model.MarketPriceUIModel
import com.eslam.bitcoin.marketprice.model.State
import com.eslam.bitcoin.util.IStringProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import java.net.ConnectException
import java.net.SocketException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * The view model attached to the MarketPriceActivity
 * Responsible for fetching data from the domain layer [IMarketPriceInteractor],
 * converting them to [MarketPriceUIModel] using the help of a [mapper],
 * and acts as the Single Source of Truth for the data and its [state]
 */
class MarketPriceViewModel : ViewModel() {

    @Inject
    lateinit var interactor: IMarketPriceInteractor
    @Inject
    lateinit var mapper: Function<MarketPriceResponse, MarketPriceUIModel>
    @Inject
    lateinit var stringProvider: IStringProvider

    private val state by lazy { MutableLiveData<State>() }
    private val compositeDisposable by lazy { CompositeDisposable() }

    /**
     * Explicit getter to expose LiveData instead of MutableLiveData
     */
    fun getState(): LiveData<State> = state


    /**
     * Retrieves the data from the interactor, and sets the data and state in the LiveData holders
     */
    fun loadData() {
        val disposable = interactor.loadData()
            .doOnSubscribe { state.value = State.LOADING }
            .map { mapper.apply(it) }
            .subscribeBy(
                onSuccess = { model -> state.value = State.SUCCESS(model) },
                onError = { throwable -> state.value = mapError(throwable) }
            )

        compositeDisposable.add(disposable)
    }

    /**
     * Returns [State.ERROR] with appropriate message based on type of [throwable]
     */
    private fun mapError(throwable: Throwable): State.ERROR {
        val message = when (throwable) {
            is ConnectException, is SocketException, is UnknownHostException ->
                stringProvider.getString(R.string.network_error_message)
            else -> stringProvider.getString(R.string.general_error_message)
        }

        return State.ERROR(message)
    }

    /**
     * Disposes all subscriptions in [compositeDisposable] when ViewModel is finally destroyed
     */
    override fun onCleared() {
        compositeDisposable.dispose()
    }
}
