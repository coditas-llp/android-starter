package com.coditas.example.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.coditas.example.R
import com.coditas.example.data.dto.GenericResponse
import com.coditas.example.data.dto.User
import com.coditas.example.repository.UserRepository
import com.coditas.resumebuilder.app.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    application: Application,
    private val userRepository: UserRepository
) : AndroidViewModel(application){

    private val _isLoading by lazy {
        MutableLiveData<Boolean>()
    }

    val isLoading:LiveData<Boolean> = _isLoading

    fun showProgressbar(boolean: Boolean){
        _isLoading.postValue(boolean)
    }

    fun loginUser(user: User): LiveData<NetworkResult<GenericResponse<User>>> {
        return MutableLiveData<NetworkResult<GenericResponse<User>>>().apply {
            postValue(NetworkResult.Loading())
            viewModelScope.launch {
                val response = userRepository.loginUser(user)
                if (response.isSuccessful) {
                    postValue(NetworkResult.Success(response.body()))
                } else if (!response.isSuccessful) {
                    postValue(NetworkResult.Error(userRepository.getError(response.errorBody())))
                } else {
                    postValue(NetworkResult.Error(getApplication<Application>().getString(R.string.txt_something_went_wrong)))
                }
            }
        }
    }
}