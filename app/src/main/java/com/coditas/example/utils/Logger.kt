package com.coditas.example.utils

import android.util.Log

object Logger {
    private const val TAG = "TAG"

    fun debug(message: String){
        Log.d(TAG,message)
    }

    fun verbose(message: String){
        Log.v(TAG,message)
    }

    fun error(message: String){
        Log.e(TAG,message)
    }

    fun info(message: String){
        Log.i(TAG,message)
    }

    fun warn(message: String){
        Log.w(TAG,message)
    }
}