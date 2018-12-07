package com.tangxb.learnkotlin

import com.tangxb.learnkotlin.api.ApiConst
import com.tangxb.learnkotlin.okhttp.HttpLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRxClient private constructor() {
    var retrofit: Retrofit

    init {
        val builder = OkHttpClient().newBuilder()
        val logInterceptor = HttpLoggingInterceptor(HttpLogger())
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
//        builder.addInterceptor(HeaderInterceptor())
//        builder.addNetworkInterceptor(StethoInterceptor())
        builder.addInterceptor(logInterceptor)
        val client = builder.build()
        retrofit = Retrofit.Builder()
                //设置OKHttpClient
                .client(client)
                //baseUrl
                .baseUrl(ApiConst.BASE_URL)
                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    companion object {
        val instance: RetrofitRxClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitRxClient()
        }
    }

}