package com.app.crypto.data.source.remote

import com.app.crypto.data.Cryptocurrency
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by admin on 04/04/18.
 */
interface ApiInterface {

    @GET("ticker/")
    fun getCryptocurrencies(@Query("start") start: String): Observable<List<Cryptocurrency>>
}