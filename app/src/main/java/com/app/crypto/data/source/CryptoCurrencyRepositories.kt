package com.app.crypto.data.source

import android.util.Log
import com.app.crypto.data.Cryptocurrency
import com.app.crypto.data.source.local.CryptoCurrencyDao
import com.app.crypto.data.source.remote.ApiInterface
import com.app.crypto.utils.Constants
import com.app.crypto.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by admin on 04/04/18.
 */
class CryptoCurrencyRepositories @Inject constructor(val apiInterface: ApiInterface, val cryptoCurrencyDao: CryptoCurrencyDao, val utils: Utils) {


    fun getCryptoCurrencies(limit: Int, offset: Int): Observable<List<Cryptocurrency>> {

        val hasInternetConnection = utils.isConnectedToInternet()

        var observableFromApi: Observable<List<Cryptocurrency>>? = null

        if (hasInternetConnection) {

            observableFromApi = getCryptoCurrenciesFromApi()
        }

        val observableFromDB = getCryptocurrenciesFromDb(limit, offset)

        return if (hasInternetConnection) Observable.concatArrayEager(observableFromApi, observableFromDB)
        else observableFromDB
    }

    private fun getCryptoCurrenciesFromApi(): Observable<List<Cryptocurrency>>? {

        return apiInterface.getCryptocurrencies(Constants.START_ZERO_VALUE)
                .doOnNext {
                    Log.e("REPOSITORY API * ", it.size.toString())

                    for (item in it) {
                        cryptoCurrencyDao.insertCryptoCurrencies(item)
                    }
                }
    }


    fun getCryptocurrenciesFromDb(limit: Int, offset: Int):Observable<List<Cryptocurrency>>{
        return cryptoCurrencyDao.queryCryptoCurrencies(limit, offset)
                .toObservable()
                .doOnNext {
                    //Print log it.size :)
                    Log.e("REPOSITORY DB *** ", it.size.toString())
                }
    }

}