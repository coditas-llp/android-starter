package com.coditas.datamodule.repositories

import com.coditas.datamodule.api.ApiService
import com.coditas.datamodule.dao.ResponseListUsers
import com.coditas.datamodule.network.BaseApiResponse
import com.coditas.datamodule.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val apiService: ApiService
) : BaseApiResponse() {
    suspend fun getAllUsers(): Flow<NetworkResult<ResponseListUsers>> {
        return flow {
            emit(safeApiCall { apiService.getAllUsers() })
        }
    }
}