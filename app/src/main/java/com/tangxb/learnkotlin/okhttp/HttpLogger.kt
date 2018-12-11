package com.tangxb.learnkotlin.okhttp

import com.tangxb.learnkotlin.util.MLogUtils
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.StringBuilder

class HttpLogger : HttpLoggingInterceptor.Logger {
    private val mMessage: StringBuilder = StringBuilder()

    companion object {
        val TAG: String = HttpLogger.javaClass.simpleName
    }

    override fun log(message: String?) {
        try {
            // 请求或者响应开始
            if (message!!.startsWith("--> POST")) {
                mMessage.setLength(0)
            }
            var tempMessage: String? = null
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if (message.startsWith("{") && message.endsWith("}") || message.startsWith("[") && message.endsWith("]")) {
                tempMessage = JsonForOkHttp.formatJson(message)
            }
            when (tempMessage) {
                null -> mMessage.append(message + "\n")
                else -> mMessage.append(tempMessage + "\n")
            }
            // 请求或者响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                MLogUtils.loggerD(TAG, mMessage)
            }
        } catch (e: Exception) {
        }
    }
}