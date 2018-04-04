package com.app.crypto.di.modules

import com.app.crypto.ui.CryptoCurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by admin on 04/04/18.
 */

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeCryptoCurrenciesActivity(): CryptoCurrencyActivity
}