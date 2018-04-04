package com.app.crypto.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.app.crypto.data.source.local.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by admin on 04/04/18.
 */

@Module
class AppModule(val app : Application) {


    @Provides
    @Singleton
    fun provideApplication():Application = app


    @Provides
    @Singleton
    fun provideCryptocurrenciesDatabase(app: Application) : Database = Room.databaseBuilder(
            app, Database::class.java,"cryptocurrencies_db"
    ).build()


    @Provides
    @Singleton
    fun provideCryptoCurrenciesDao(database: Database): CryptoCurrencyDao = database.cryptoCurrencyDao()
}