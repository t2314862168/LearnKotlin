package com.tangxb.learnkotlin.bean

class BaseBean<T> {
    var errorCode: Int = 0
    var errorMsg: String? = null
    var data: T? = null
    var throwable: Throwable? = null
}