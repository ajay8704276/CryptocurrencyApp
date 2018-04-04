package com.app.crypto

import android.app.Activity
import android.app.Application
import com.app.crypto.di.components.DaggerAppComponent
import com.app.crypto.di.modules.AppModule
import com.app.crypto.di.modules.NetModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

@Suppress("DEPRECATION")
/**
 * Created by admin on 04/04/18.
 */
class CryptoApplication :Application() , HasActivityInjector {

    @Inject lateinit var activityInjector : DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        /**
         * Initialise Dagger components and modules
         */
        DaggerAppComponent.builder()
                .netModule(NetModule(BuildConfig.URL))
                .appModule(AppModule(this)).build().inject(this)

    }


    override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}