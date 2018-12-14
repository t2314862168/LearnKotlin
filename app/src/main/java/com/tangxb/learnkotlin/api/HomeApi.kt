package com.tangxb.learnkotlin.api

import com.tangxb.learnkotlin.bean.ArticleBeanComplex
import com.tangxb.learnkotlin.bean.BannerBean
import com.tangxb.learnkotlin.bean.BaseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {
    @GET("banner/json")
    fun getBanner(): Observable<BaseBean<List<BannerBean>>>

    @GET("article/list/{pageNum}/json")
    fun getArticle(@Path("pageNum") pageNum: Int = 0): Observable<BaseBean<ArticleBeanComplex>>

}