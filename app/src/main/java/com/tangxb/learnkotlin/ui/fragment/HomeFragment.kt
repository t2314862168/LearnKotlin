package com.tangxb.learnkotlin.ui.fragment

import android.content.Intent
import butterknife.BindView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.tangxb.learnkotlin.R
import com.tangxb.learnkotlin.bean.ArticleBeanComplex
import com.tangxb.learnkotlin.bean.BannerBean
import com.tangxb.learnkotlin.bean.BaseBean
import com.tangxb.learnkotlin.bean.HomeBeanComplex
import com.tangxb.learnkotlin.controller.HomeFragmentController
import com.tangxb.learnkotlin.loader.GlideImageLoader
import com.tangxb.learnkotlin.ui.BaseFragment
import com.tangxb.learnkotlin.ui.activity.MainActivity
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer


/**
 * https://www.jianshu.com/p/94c433057440(Rxjava 中 merge 和 contact 区别)
 * https://blog.csdn.net/mwthe/article/details/82780193(RxJava2.0第四篇之 zip、concat 、merge 合并发送器)
 */
class HomeFragment : BaseFragment() {
    @BindView(R.id.refresh_layout)
    lateinit var refreshLayout: SmartRefreshLayout
    @BindView(R.id.banner)
    lateinit var banner: Banner
    var pageNum = 0

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
        banner.setBannerAnimation(Transformer.Default)
        //设置自动轮播，默认为true
        banner.isAutoPlay(true)
        //设置轮播时间
        banner.setDelayTime(5000)
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER)
        refreshLayout.setEnableRefresh(true)
        refreshLayout.setEnableLoadMore(true)
        refreshLayout.setEnableAutoLoadMore(true)
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {

            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                refreshData()
            }
        })

        val observable1 = Observable.create(ObservableOnSubscribe<String> { emitter -> emitter.onNext("First") })
        val observable2 = Observable.create(ObservableOnSubscribe<String> { emitter -> emitter.onNext("Second") })
        Observable.concat(observable1, observable2)
                .subscribe { Consumer<Any> { t -> logE("Observable.concat========$t") } }
        refreshData()
    }

    fun refreshData() {
        pageNum = 0
        addDisposableIoMain(Observable.zip(controller.getBanner(), controller.getArticle(pageNum)
                , BiFunction<BaseBean<List<BannerBean>>, BaseBean<ArticleBeanComplex>, BaseBean<HomeBeanComplex>> { t1, t2 ->
            val baseBean = BaseBean<HomeBeanComplex>()
            baseBean.errorCode = t1.errorCode
            baseBean.data = HomeBeanComplex(t1.data!!, t2.data!!)
            baseBean
        }), Consumer { t ->
            val imgList = mutableListOf<String>()
            val titleList = mutableListOf<String>()
            for (v in t.data!!.banner) {
                imgList.add(v.imagePath)
                titleList.add(v.title)
            }
            banner.setImages(imgList)
            banner.setBannerTitles(titleList)
            banner.start()

            logE("t.data============${t.data!!.article}")
            refreshLayout.finishRefresh()
            refreshLayout.finishLoadMore()
        })
    }

    fun loadmoreData() {
        pageNum++
        controller.getArticle(pageNum)
                .flatMap { t -> Observable.just(t.data!!.datas) }
    }

    fun testGo() {
        val intent = Intent(mActivity, MainActivity::class.java)
        startActivity(intent)
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