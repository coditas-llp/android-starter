package com.coditas.example.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.coditas.example.R
import com.coditas.example.utils.AppConstants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var splashOnScreen = true
        installSplashScreen().setKeepOnScreenCondition{ splashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed(
            {
                //Dismiss splash screen after given delay and navigate to start destination of nav graph
                splashOnScreen = false
            },AppConstants.DELAY_2000)
    }
}