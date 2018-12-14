package com.tangxb.learnkotlin.controller

import com.tangxb.learnkotlin.RetrofitRxClient
import com.tangxb.learnkotlin.api.HomeApi

class HomeFragmentController {
    private fun getHomeApi() = RetrofitRxClient.instance.retrofit.create(HomeApi::class.java)

    fun getBanner() = getHomeApi().getBanner()

    fun getArticle(pageNum: Int) = getHomeApi().getArticle(pageNum)
}