package com.coditas.datamodule.api

import com.coditas.datamodule.dao.ResponseListUsers
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    //TODO: Add you api end points here.
    @GET("/api/users?page=2")
    suspend fun getAllUsers(): Response<ResponseListUsers>

}