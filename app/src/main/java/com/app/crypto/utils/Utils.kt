package com.app.crypto.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

/**
 * Created by admin on 04/04/18.
 */
class Utils @Inject constructor(private val context: Context) {

    @SuppressLint("MissingPermission")
    fun isConnectedToInternet(): Boolean {
        val connectivity = context.getSystemService(
                Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null)
                for (i in info.indices)
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
        }
        return false
    }
}