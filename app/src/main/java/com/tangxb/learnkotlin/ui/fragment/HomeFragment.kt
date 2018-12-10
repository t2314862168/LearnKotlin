package com.tangxb.learnkotlin.ui.fragment

import com.tangxb.learnkotlin.R
import com.tangxb.learnkotlin.controller.HomeFragmentController
import com.tangxb.learnkotlin.ui.BaseFragment
import io.reactivex.functions.Consumer

class HomeFragment : BaseFragment() {
    override fun getLayoutResId() = R.layout.fragment_home
    private val controller: HomeFragmentController = HomeFragmentController()

    companion object {
        val TAG: String = HomeFragment.javaClass.simpleName
        fun getInstance() = HomeFragment()
    }

    override fun initData() {
        addDisposableIoMain(controller.getBanner(), Consumer { t -> logE(t?.data.toString()) })
    }
}