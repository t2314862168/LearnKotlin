package com.tangxb.learnkotlin

import android.content.Context
import android.os.Environment
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.module.AppGlideModule
import java.lang.Exception

@GlideModule
class MyGlideModule : AppGlideModule() {
    companion object {
        /**
         * 1M = 1024k
         * 1k = 1024字节
         * 20M的大小
         */
        const val DISK_CACHE_SIZE = 20 * 1024 * 1024L
        const val DISK_CACHE_NAME = "glide"
    }

    /**
     * Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
     * Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
     *
     * @param context
     * @param builder
     */
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            try {
                builder.setDiskCache(DiskLruCacheFactory(context.externalCacheDir.absolutePath, DISK_CACHE_NAME, DISK_CACHE_SIZE))
            } catch (e: Exception) {
            }
        }
    }
}