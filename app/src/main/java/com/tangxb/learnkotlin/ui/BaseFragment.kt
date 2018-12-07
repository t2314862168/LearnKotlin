package com.tangxb.learnkotlin.ui

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {
    var mLayoutResId: Int = 0
    var mView: View? = null
    var mClassName: String? = null
    var mActivity: Activity? = null
    var mResources: Resources? = null
    abstract fun getLayoutResId(): Int
    open fun getLayoutResView(): View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receivePassData(arguments)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mClassName = javaClass.simpleName
        mActivity = activity
        mResources = resources
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
        initView()
        initData()
        initListener()
    }

    open fun receivePassData(savedInstanceState: Bundle?) {

    }

    open fun initView() {

    }

    open fun initData() {

    }

    open fun initListener() {

    }
}