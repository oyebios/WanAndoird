package com.lw.network.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit

abstract class BaseNetApi {
    var retrofit : Retrofit ?= null
    var httpClient : OkHttpClient ?= null

    fun <T> getApiService(apiService : Class<T>):T{
        if (retrofit == null){
            retrofit = configRetrofitBuilder()
                .client(configOkHttp().build())
                .build()
        }
        return retrofit!!.create(apiService)
    }

    abstract fun configRetrofitBuilder():Retrofit.Builder

    abstract fun configOkHttp():OkHttpClient.Builder

}