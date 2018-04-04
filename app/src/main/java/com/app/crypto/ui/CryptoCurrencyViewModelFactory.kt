package com.app.crypto.ui

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject

/**
 * Created by admin on 04/04/18.
 */
class CryptocurrenciesViewModelFactory @Inject constructor(

        private val cryptocurrenciesViewModel: CryptoCurrencyViewModel) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CryptoCurrencyViewModel::class.java!!)) {
            return cryptocurrenciesViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}