package com.coditas.example.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coditas.example.R
import com.coditas.example.data.dto.GenericResponse
import com.coditas.example.repository.UserRepository
import com.coditas.resumebuilder.app.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : AndroidViewModel(application) {

    fun getUserInfo(userId: Any): LiveData<NetworkResult<GenericResponse<Any>>> {
        return MutableLiveData<NetworkResult<GenericResponse<Any>>>().apply {
            postValue(NetworkResult.Loading())
            viewModelScope.launch {
                val response = userRepository.getUserInfo(userId)
                if (response.isSuccessful) {
                    postValue(NetworkResult.Success(response.body()))
                } else {
                    postValue(NetworkResult.Error(userRepository.getError(response.errorBody())))
                }
            }
        }
    }
}