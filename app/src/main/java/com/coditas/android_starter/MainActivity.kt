package com.coditas.android_starter

import MyNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.coditas.android_starter.screens.screenOne.ScreenOne
import com.coditas.android_starter.ui.theme.AndroidcomposestarterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidcomposestarterTheme {
                val navController = rememberNavController()
                MyNavHost(navController)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScreenOne(navController = rememberNavController())
}