package com.kelviniyalo.moviesapp.common

import com.kelviniyalo.moviesapp.domain.provider.AppConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class NetworkClient @Inject constructor(val appConfig: AppConfig) {

    inline fun <reified T> getApiService(
        baseUrl: String
    ): T {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(appConfig.okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(T::class.java)
    }
}