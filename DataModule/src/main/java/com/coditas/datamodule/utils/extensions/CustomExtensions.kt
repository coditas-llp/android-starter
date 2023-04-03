package com.coditas.datamodule.utils.extensions

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

fun Context.isInternetConnected(): Boolean {
    with(getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) {
        getNetworkCapabilities(activeNetwork)?.let {
            return (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) && it.hasCapability(
                NetworkCapabilities.NET_CAPABILITY_VALIDATED
            ))
        }
    }
    return false
}