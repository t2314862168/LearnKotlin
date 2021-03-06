package com.tangxb.learnkotlin.okhttp

import android.text.TextUtils

class JsonForOkHttp {
    companion object {
        private fun addIndentBlank(sb: java.lang.StringBuilder, indent: Int) {
            for (i in 0 until indent) {
                sb.append('\t')
            }
        }

        fun formatJson(jsonStr: String?): String {
            if (TextUtils.isEmpty(jsonStr)) return ""
            val sb = StringBuilder()
            var last: Char
            var current = '\u0000'
            var indent = 0
            for (i in 0 until jsonStr!!.length) {
                last = current
                current = jsonStr[i]
                //遇到{ [换行，且下一行缩进
                when (current) {
                    '{', '[' -> {
                        sb.append(current)
                        sb.append('\n')
                        indent++
                        addIndentBlank(sb, indent)
                    }
                //遇到} ]换行，当前行缩进
                    '}', ']' -> {
                        sb.append('\n')
                        indent--
                        addIndentBlank(sb, indent)
                        sb.append(current)
                    }
                //遇到,换行
                    ',' -> {
                        sb.append(current)
                        if (last != '\\') {
                            sb.append('\n')
                            addIndentBlank(sb, indent)
                        }
                    }
                    else -> sb.append(current)
                }
            }
            return sb.toString()
        }
    }
}