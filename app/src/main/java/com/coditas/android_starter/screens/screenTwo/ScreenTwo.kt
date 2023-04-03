package com.coditas.android_starter.screens.screenTwo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coditas.android_starter.R

@Composable
fun ScreenTwo(navController: NavHostController, param: String?) {
    val screenTwoViewModel: ScreenTwoViewModel = hiltViewModel()
    Surface(modifier = Modifier.fillMaxWidth()) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${stringResource(id = R.string.screen_two_dc_title)} $param",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenTwoPreview() {
    val navController = rememberNavController()
    val arg1 = "Argument in String"
    ScreenTwo(navController = navController, param = arg1)
}