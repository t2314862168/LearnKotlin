package com.tangxb.learnkotlin.ui

import android.util.Log
import com.tangxb.learnkotlin.R
import com.tangxb.learnkotlin.RetrofitRxClient
import com.tangxb.learnkotlin.api.BannerApi
import com.tangxb.learnkotlin.bean.BannerBean
import com.tangxb.learnkotlin.bean.BaseBean
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.function.Consumer

class HomeFragment : BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_home

    companion object {
        val TAG: String = HomeFragment.javaClass.simpleName
        fun getInstance() = HomeFragment()
    }


    lateinit var compositeDisposable: CompositeDisposable
    fun <T> addDisposableIoMain(observable: Observable<BaseBean<T>>, consumer: Consumer<BaseBean<T>>) {
        when (compositeDisposable) {
            null -> compositeDisposable = CompositeDisposable()
        }
        val subscribe = observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn({ t ->
                    val baseBean = BaseBean<T>()
                    baseBean.throwable = t
                    baseBean
                })
                .subscribe({ _: BaseBean<T>? -> consumer })
        compositeDisposable.add(subscribe)
    }

    override fun initData() {
        RetrofitRxClient.instance.retrofit.create(BannerApi::class.java).getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn({ t ->
                    val baseBean = BaseBean<BannerBean>()
                    baseBean.throwable = t
                    baseBean
                })
                .subscribe({ t -> Log.e("SubscribeError", t?.throwable.toString()) })
    }
}