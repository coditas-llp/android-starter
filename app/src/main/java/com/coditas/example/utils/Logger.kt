package com.coditas.example.utils

import android.util.Log
import com.coditas.example.BuildConfig

object Logger {

    @JvmStatic
    fun <T : Any> T.logError(logMessage: String) {
        if (BuildConfig.DEBUG)
            Log.e(this.javaClass.simpleName, logMessage)
    }

    @JvmStatic
    fun <T : Any> T.logVerbose(logMessage: String) {
        if (BuildConfig.DEBUG)
            Log.v(this.javaClass.simpleName, logMessage)
    }

    @JvmStatic
    fun <T : Any> T.logDebug(logMessage: String) {
        if (BuildConfig.DEBUG)
            Log.d(this.javaClass.simpleName, logMessage)
    }

    @JvmStatic
    fun <T : Any> T.logWarn(logMessage: String) {
        if (BuildConfig.DEBUG)
            Log.w(this.javaClass.simpleName, logMessage)
    }

    @JvmStatic
    fun <T : Any> T.logInfo(logMessage: String) {
        if (BuildConfig.DEBUG)
            Log.i(this.javaClass.simpleName, logMessage)
    }

}