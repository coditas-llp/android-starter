package com.coditas.example.data.remote

import com.coditas.example.data.dto.GenericResponse
import com.coditas.example.data.dto.User
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    //Login API
    @POST("/auth/client/login")
    suspend fun loginUser(@Body user: User) : Response<GenericResponse<User>>

    @GET("/user/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: Int) : Response<GenericResponse<User>>
}