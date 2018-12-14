package com.tangxb.learnkotlin.bean

data class HomeBeanComplex(
        val banner: List<BannerBean>,
        val article: ArticleBeanComplex
)