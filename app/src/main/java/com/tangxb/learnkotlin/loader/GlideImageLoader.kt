package com.tangxb.learnkotlin.loader

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.tangxb.learnkotlin.GlideApp
import com.youth.banner.loader.ImageLoader

class GlideImageLoader : ImageLoader() {
    private val requestOptions = RequestOptions()

    init {
        requestOptions.dontAnimate()
                .centerCrop()
    }

    override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
        GlideApp.with(context!!).load(path).apply(requestOptions).into(imageView!!)
    }
}