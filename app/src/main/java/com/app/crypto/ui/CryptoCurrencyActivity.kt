package com.app.crypto.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.app.crypto.R
import com.app.crypto.api.ApiClient
import com.app.crypto.data.Cryptocurrency
import com.app.crypto.data.source.remote.ApiInterface
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class CryptoCurrencyActivity : AppCompatActivity() {
    val compositeDisposable = io.reactivex.disposables.CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        showCryptoCurrencies()
    }

    private fun showCryptoCurrencies() {


        val cryptoCurrenciesResponse = getCryptoCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())


        val disposableObserver = cryptoCurrenciesResponse.subscribeWith(object :DisposableObserver<List<Cryptocurrency>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Cryptocurrency>) {

                val listSize = t.size
                Log.e("ITEMS **** ", listSize.toString())
            }

            override fun onError(e: Throwable) {
                Log.e("ERROR *** ", e.message)
            }

        })

        compositeDisposable.add(disposableObserver)
    }

    private fun getCryptoCurrencies(): Observable<List<Cryptocurrency>> {

        val retrofit = ApiClient.getClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        return apiInterface.getCryptocurrencies("0")
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
        super.onDestroy()
    }
}
