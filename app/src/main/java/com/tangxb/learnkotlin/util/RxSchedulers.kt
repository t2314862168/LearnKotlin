package com.tangxb.learnkotlin.util

import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RxSchedulers {
    companion object {
        fun <T> ioToMain() = ObservableTransformer<T, T> { upstream ->
            upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .unsubscribeOn(Schedulers.io())
        }

        fun <T> io() = ObservableTransformer<T, T> { upstream ->
            upstream.subscribeOn(Schedulers.io())
        }
    }
}