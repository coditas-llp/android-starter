package com.coditas.example.data.remote

import android.content.Context
import com.coditas.example.data.local.AccessTokenSharedPreference
import com.coditas.example.utils.AppConstants
import com.coditas.example.utils.InternetConnection
import com.coditas.example.utils.Logger
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomInterceptor @Inject constructor(
    private val accessTokenSharedPreference: AccessTokenSharedPreference,
    @ApplicationContext val context: Context
) : Interceptor {

    private lateinit var apiResponse: Response

    companion object {
        private const val AUTHORIZATION = "Authorization"
        const val UNKNOWN_ERROR_CODE = 1
        const val NETWORK_ERROR_CODE = 2
        private const val ERROR_BODY = "Something went wrong"
        private const val JSON = "application/json"

    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val token = accessTokenSharedPreference.getToken()
        request = request.newBuilder().header(AUTHORIZATION, "Bearer $token").build()


        apiResponse = if (InternetConnection().checkConnection(context)) {
            try {
                chain.proceed(request)
            } catch (e: Exception) {
                getLocalErrorResponse(request, e.message.orEmpty(), UNKNOWN_ERROR_CODE)
            }
        } else {
            getLocalErrorResponse(request, AppConstants.NO_INTERNET, NETWORK_ERROR_CODE)
        }
        Logger.debug(apiResponse.code.toString())
        return apiResponse
    }

    private fun getLocalErrorResponse(request: Request, errorMessage: String, code: Int): Response {
        return Response.Builder()
            .body(ERROR_BODY.toResponseBody(JSON.toMediaTypeOrNull()))
            .code(code)
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message(errorMessage)
            .build()
    }
}