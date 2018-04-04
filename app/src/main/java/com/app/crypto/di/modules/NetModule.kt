package com.app.crypto.di.modules

import com.app.crypto.data.source.remote.ApiInterface
import com.squareup.moshi.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by admin on 04/04/18.
 */


@Module
class NetModule(private val baseUrl:String ) {


    @Provides
    @Singleton
    fun provideOhkHttpClient() :OkHttpClient = OkHttpClient.Builder().build()


    @Provides
    @Singleton
    fun provideMoshi():Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient,moshi: Moshi):Retrofit {

        return Retrofit.Builder().client(okHttpClient)
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

    }


    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)


}