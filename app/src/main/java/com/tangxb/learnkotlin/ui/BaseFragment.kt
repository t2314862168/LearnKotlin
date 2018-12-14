package com.tangxb.learnkotlin.ui

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.tangxb.learnkotlin.MApplication
import com.tangxb.learnkotlin.bean.BaseBean
import com.tangxb.learnkotlin.util.RxSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

abstract class BaseFragment : Fragment() {
    abstract fun getLayoutResId(): Int
    open fun getLayoutResView(): View? = null

    var mLayoutResId: Int = 0
    var mView: View? = null
    var mClassName: String? = null
    var mActivity: Activity? = null
    var mApplication: MApplication? = null
    var mResources: Resources? = null
    var compositeDisposable: CompositeDisposable? = null
    var unbinder: Unbinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mClassName = javaClass.simpleName
        mActivity = activity
        mApplication = activity!!.application as MApplication?
        mResources = resources
        receivePassData(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (getLayoutResView() != null) {
            mView = getLayoutResView()
        } else {
            mLayoutResId = getLayoutResId()
            when (mLayoutResId) {
                0 -> throw IllegalStateException("getLayoutResId() should be override")
            }
            mView = inflater.inflate(mLayoutResId, container, false)
        }
        init()
        return mView
    }

    private fun init() {
        bindButterKnife()
        initView()
        initData()
        initListener()
    }

    private fun bindButterKnife() {
        unbinder = ButterKnife.bind(this, mView!!)
    }

    private fun unbindButterKnife() {
        unbinder?.unbind()
    }

    fun <T> addDisposableIoMain(observable: Observable<BaseBean<T>>, consumer: Consumer<BaseBean<T>>) {
        if (compositeDisposable === null) {
            compositeDisposable = CompositeDisposable()
        }
        val subscribe = observable.compose(RxSchedulers.ioToMain())
                .onErrorReturn({ t ->
                    val baseBean = BaseBean<T>()
                    baseBean.throwable = t
                    baseBean
                })
                .subscribe(consumer)
        compositeDisposable?.add(subscribe)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
        compositeDisposable = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unbindButterKnife()
    }

    open fun receivePassData(savedInstanceState: Bundle?) {

    }

    open fun initView() {

    }

    open fun initData() {

    }

    open fun initListener() {

    }

    fun logD(obj: String) {
        mApplication!!.logD(mClassName!!, obj)
    }

    fun logD(tag: String, obj: String) {
        mApplication!!.logD(tag, obj)
    }

    fun logE(obj: String) {
        mApplication!!.logE(mClassName!!, obj)
    }

    fun logE(tag: String, obj: String) {
        mApplication!!.logE(tag, obj)
    }
}