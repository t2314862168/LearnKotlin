package com.tangxb.learnkotlin.ui.fragment

import butterknife.BindView
import com.tangxb.learnkotlin.R
import com.tangxb.learnkotlin.controller.HomeFragmentController
import com.tangxb.learnkotlin.loader.GlideImageLoader
import com.tangxb.learnkotlin.ui.BaseFragment
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
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
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        //设置轮播时间
        banner.setDelayTime(1500)
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER)
        addDisposableIoMain(controller.getBanner(), Consumer { t ->
            val imgList = mutableListOf<String>()
            val titleList = mutableListOf<String>()
            for (v in t.data!!) {
                imgList.add(v.imagePath)
                titleList.add(v.desc)
            }
            banner.setImages(imgList)
            banner.setBannerTitles(titleList)
            banner.start()
        })
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }
}