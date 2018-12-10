package com.tangxb.learnkotlin.util

import android.util.Log
import com.orhanobut.logger.Logger
import com.tangxb.learnkotlin.BuildConfig

class MLogUtils {
    companion object {
        private val DEBUG: Boolean = BuildConfig.DEBUG

        fun loggerD(tag: String, obj: Any) {
            if (DEBUG) {
                Logger.t(tag).d(obj)
            }
        }

        fun loggerJson(tag: String, obj: String) {
            if (DEBUG) {
                Logger.t(tag).json(obj)
            }
        }

        fun d(tag: String, obj: String) {
            Log.d(tag, obj)
        }

        fun e(tag: String, obj: String) {
            Log.e(tag, obj)
        }
    }
}