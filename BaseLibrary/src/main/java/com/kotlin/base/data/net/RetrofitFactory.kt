package com.kotlin.base.data.net

import com.kotlin.base.common.BaseConstant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitFactory private constructor(){
    companion object {
        val instance : RetrofitFactory by lazy {
            RetrofitFactory()

        }
    }

    private val retrofit:Retrofit
    private val interceptor:Interceptor
    init {
        interceptor = Interceptor {

                chain ->
            val request = chain.request()
                .newBuilder()
                .addHeader("Content-type", "application/json")
                .addHeader("charset","utf-8")
//                .addHeader("Authorization","Basic Y29tLm1hbndlaS5kZXY6MTIzNDU2")
                .build()
            chain.proceed(request)

        }
        retrofit = Retrofit.Builder()
            .baseUrl(BaseConstant.SERVER_ADDRESS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(initClient())
            .build()
    }

    private fun initClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor (interceptor)
            .addInterceptor(initLogInterceptor())
            .connectTimeout(10,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build()
    }

    //日志拦截器
    private fun initLogInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    fun <T> create(server:Class<T>):T{
        return retrofit.create(server)
    }
}
