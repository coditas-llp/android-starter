package com.coditas.example.repository

import com.coditas.example.data.dto.GenericResponse
import com.coditas.example.data.dto.User
import com.coditas.example.data.remote.APIService
import com.coditas.example.utils.AppConstants.ERROR_MESSAGE
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val apiService: APIService) {

    suspend fun loginUser(user: User): Response<GenericResponse<User>> {
        return apiService.loginUser(user)
    }

    suspend fun getUserInfo(userId: Int) : Response<GenericResponse<User>>{
        return apiService.getUserInfo(userId)
    }

    fun getError(errorResponse: ResponseBody?): String? {
        val errorObj = errorResponse?.charStream()?.readText()?.let { JSONObject(it) }
        return errorObj?.getString(ERROR_MESSAGE)
    }

}

