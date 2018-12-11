package com.tangxb.learnkotlin.api

import com.tangxb.learnkotlin.bean.BannerBean
import com.tangxb.learnkotlin.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET

interface BannerApi {
    @GET("banner/json")
    fun getBanner(): Observable<BaseBean<List<BannerBean>>>
}