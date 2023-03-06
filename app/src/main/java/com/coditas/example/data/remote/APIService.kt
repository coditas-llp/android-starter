package com.coditas.example.data.remote

import com.coditas.example.data.dto.GenericResponse
import retrofit2.Response
import retrofit2.http.*

interface APIService {

    @POST("/auth/client/login")
    suspend fun loginUser(@Body user: Any) : Response<GenericResponse<Any>>

    @GET("/user/{userId}")
    suspend fun getUserInfo(@Path("userId") userId: Any) : Response<GenericResponse<Any>>

    @PUT("/users/update/{userId}")
    suspend fun updateUserInfo(@Path("userId") userId: Any): Response<GenericResponse<Any>>

    @DELETE("/users/update/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Any): Response<GenericResponse<Any>>

}