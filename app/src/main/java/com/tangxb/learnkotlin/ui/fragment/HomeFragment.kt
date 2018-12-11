package com.tangxb.learnkotlin.ui.fragment

import butterknife.BindView
import com.tangxb.learnkotlin.R
import com.tangxb.learnkotlin.controller.HomeFragmentController
import com.tangxb.learnkotlin.loader.GlideImageLoader
import com.tangxb.learnkotlin.ui.BaseFragment
import com.youth.banner.Banner
import io.reactivex.functions.Consumer

class HomeFragment : BaseFragment() {
    @BindView(R.id.banner)
    lateinit var banner: Banner

    override fun getLayoutResId() = R.layout.fragment_home
    private val controller: HomeFragmentController = HomeFragmentController()

    companion object {
        val TAG: String = HomeFragment.javaClass.simpleName
        fun getInstance() = HomeFragment()
    }

    override fun initData() {
        banner.setImageLoader(GlideImageLoader())
        addDisposableIoMain(controller.getBanner(), Consumer { t ->
            val list = mutableListOf<String>()
            for (v in t.data!!) {
                list.add(v.imagePath)
            }
            banner.update(list)
        })
    }

    override fun onResume() {
        super.onResume()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }
}