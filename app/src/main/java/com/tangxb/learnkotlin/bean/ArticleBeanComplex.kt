package com.tangxb.learnkotlin.bean

data class ArticleBeanComplex(
        val curPage: Int,
        val offset: Int,
        val over: Boolean,
        val pageCount: Int,
        val datas: List<ArticleBean>,
        val size: Int,
        val total: Int
)