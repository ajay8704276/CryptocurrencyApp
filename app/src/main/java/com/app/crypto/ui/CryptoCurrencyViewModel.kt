package com.app.crypto.ui

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.app.crypto.data.Cryptocurrency
import com.app.crypto.data.source.CryptoCurrencyRepositories
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Created by admin on 04/04/18.
 */
class CryptoCurrencyViewModel @Inject constructor(private val cryptoCurrencyRepositories: CryptoCurrencyRepositories) : ViewModel() {

    var cryptoCurrenciesResult: MutableLiveData<List<Cryptocurrency>> = MutableLiveData()
    var cryptoCurrenciesError: MutableLiveData<String> = MutableLiveData()
    var cryptoCurrenciesLoader: MutableLiveData<Boolean> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<List<Cryptocurrency>>


    fun cryptocurrenciesResult(): LiveData<List<Cryptocurrency>> {
        return cryptoCurrenciesResult
    }

    fun cryptocurrenciesError(): LiveData<String> {
        return cryptoCurrenciesError
    }

    fun cryptocurrenciesLoader(): LiveData<Boolean> {
        return cryptoCurrenciesLoader
    }


    fun loadCryptocurrencies(limit: Int, offset: Int) {

        disposableObserver = object : DisposableObserver<List<Cryptocurrency>>() {
            override fun onComplete() {

            }

            override fun onNext(cryptocurrencies: List<Cryptocurrency>) {
                cryptoCurrenciesResult.postValue(cryptocurrencies)
                cryptoCurrenciesLoader.postValue(false)
            }

            override fun onError(e: Throwable) {
                cryptoCurrenciesError.postValue(e.message)
                cryptoCurrenciesLoader.postValue(false)
            }
        }

      
        cryptoCurrencyRepositories.getCryptoCurrencies(limit, offset)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(400, TimeUnit.MILLISECONDS)
                .subscribe(disposableObserver)
    }

    fun disposeElements() {
        if (null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }
}