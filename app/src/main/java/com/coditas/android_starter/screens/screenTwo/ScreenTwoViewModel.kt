package com.coditas.android_starter.screens.screenTwo

import androidx.lifecycle.ViewModel
import com.coditas.datamodule.repositories.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScreenTwoViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {  }