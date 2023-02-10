package com.lw.network.retrofit.service

import com.lw.network.retrofit.BaseNetApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class MainNetApi : BaseNetApi() {
    companion object {
        val INSTANCE: MainNetApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            MainNetApi()
        }
    }

    override fun configRetrofitBuilder(): Retrofit.Builder {
        return  Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()))
            .baseUrl("11")
    }

    override fun configOkHttp(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            //设置缓存配置 缓存最大10M
//            .cache(Cache(File(appContext.cacheDir, "cxk_cache"), 10 * 1024 * 1024))
            //添加Cookies自动持久化
//            .cookieJar(cookieJar)
            //示例：添加公共heads 注意要设置在日志拦截器之前，不然Log中会不显示head信息
//            .addInterceptor(MyHeadInterceptor())
            //添加缓存拦截器 可传入缓存天数，不传默认7天
//            .addInterceptor(CacheInterceptor())
            // 日志拦截器
            .addInterceptor(HttpLoggingInterceptor())
            //超时时间 连接、读、写
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
    }

}