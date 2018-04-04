package com.app.crypto.di.components

import com.app.crypto.CryptoApplication
import com.app.crypto.di.modules.AppModule
import com.app.crypto.di.modules.BuildersModule
import com.app.crypto.di.modules.NetModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by admin on 04/04/18.
 */


@Singleton
@Component(
        modules = arrayOf(AndroidInjectionModule::class, BuildersModule::class,AppModule::class,NetModule::class)
)
interface AppComponent {

    fun inject(app:CryptoApplication)
}