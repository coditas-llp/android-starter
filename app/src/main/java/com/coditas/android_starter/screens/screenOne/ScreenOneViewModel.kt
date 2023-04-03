package com.coditas.android_starter.screens.screenOne

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coditas.datamodule.dao.ResponseListUsers
import com.coditas.datamodule.network.NetworkResult
import com.coditas.datamodule.repositories.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScreenOneViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel(){
    private var _response: MutableLiveData<NetworkResult<ResponseListUsers>> = MutableLiveData()
    val response: LiveData<NetworkResult<ResponseListUsers>> = _response

    fun fetchAllUsers() = viewModelScope.launch {
        networkRepository.getAllUsers().collect{
            _response.value = it
        }
    }
}