package com.tangxb.learnkotlin.okhttp

import com.orhanobut.logger.Logger
import com.tangxb.learnkotlin.BuildConfig

class MLogUtils {
    companion object {
        private val DEBUG: Boolean = BuildConfig.DEBUG

        fun d(tag: String, obj: Any) {
            if (DEBUG) {
                Logger.t(tag).d(obj)
            }
        }

        fun json(tag: String, obj: String) {
            if (DEBUG) {
                Logger.t(tag).json(obj)
            }
        }
    }
}