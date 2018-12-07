package com.tangxb.learnkotlin.ui

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

abstract class BaseActivity : AppCompatActivity() {
    var mLayoutResId: Int = 0
    var mClassName: String? = null
    var mActivity: Activity? = null
    var mResources: Resources? = null
    abstract fun getLayoutResId(): Int
    open fun getLayoutResView(): View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutResView() != null) {
            setContentView(getLayoutResView())
        } else {
            mLayoutResId = getLayoutResId()
            when (mLayoutResId) {
                0 -> throw IllegalStateException("getLayoutResId() should be override")
            }
            setContentView(mLayoutResId)
        }
        init()
    }

    private fun init() {
        mClassName = javaClass.simpleName
        mActivity = this
        mResources = resources
        receivePassData(intent)
        initView()
        initData()
        initListener()
    }

    open fun receivePassData(intent: Intent) {

    }

    open fun initView() {
    }

    open fun initData() {
    }

    open fun initListener() {
    }
}