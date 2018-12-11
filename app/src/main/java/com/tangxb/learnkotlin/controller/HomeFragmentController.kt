package com.tangxb.learnkotlin.controller

import com.tangxb.learnkotlin.RetrofitRxClient
import com.tangxb.learnkotlin.api.BannerApi

class HomeFragmentController {
    private fun getBannerApi() = RetrofitRxClient.instance.retrofit.create(BannerApi::class.java)

    fun getBanner() = getBannerApi().getBanner()


}