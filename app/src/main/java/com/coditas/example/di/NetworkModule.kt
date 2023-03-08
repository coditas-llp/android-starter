package com.coditas.example.di

import com.coditas.example.data.remote.APIService
import com.coditas.example.data.remote.CustomInterceptor
import com.coditas.example.utils.AppConstants.BASE_URL
import com.coditas.example.utils.AppConstants.SOCKET_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private val logger: HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)
        .setLevel(HttpLoggingInterceptor.Level.HEADERS)

    private val okHttp: OkHttpClient =
        OkHttpClient.Builder().addInterceptor(logger).connectTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(SOCKET_TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Builder {
        return Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideRetrofitWithHeader(customInterceptor: CustomInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(customInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideAPI(retrofitBuilder: Builder, okHttpClient: OkHttpClient): APIService {
        return retrofitBuilder.client(okHttpClient)
            .build().create(APIService::class.java)
    }

}

