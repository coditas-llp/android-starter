package com.coditas.android_starter.screens.screenOne

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.coditas.android_starter.R
import com.coditas.datamodule.network.NetworkResult
import com.coditas.datamodule.utils.Logger
import com.coditas.datamodule.utils.Logger.logDebug
import com.coditas.datamodule.utils.extensions.isInternetConnected
import com.coditas.uimodule.components.CoditasPrimaryButtonView

@Composable
fun ScreenOne(navController: NavHostController) {
    val screenOneViewModel: ScreenOneViewModel = hiltViewModel()
    val lifecycleOwner = rememberUpdatedState(LocalLifecycleOwner.current)
    val context = LocalContext.current

    screenOneViewModel.response.observe(lifecycleOwner.value) {
        when (it) {
            is NetworkResult.Success -> {
                //TODO: Add action on success response here
                val argument1 = it.data?.data?.get(0)?.firstName
                Logger.logDebug("Json response :- ${it.data?.data?.get(0)}")
                navController.navigate("screenTwo/${argument1}")
            }
            is NetworkResult.Loading -> {
                //TODO: Add action on loading response here
            }
            is NetworkResult.Error -> {
                //TODO: Add action on error response here
            }
        }
    }

    Surface(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.screen_one_dc_title),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            CoditasPrimaryButtonView(
                buttonText = stringResource(id = R.string.screen_one_dc_button_text),
                onClickListener = {
                    if (context.isInternetConnected()){
                        screenOneViewModel.fetchAllUsers()
                    } else {
                        Toast.makeText(context, context.getText(R.string.check_internet_connectivity),Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenOnePreview() {
    ScreenOne(navController = rememberNavController())
}