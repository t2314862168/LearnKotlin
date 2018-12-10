package com.tangxb.learnkotlin

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.tangxb.learnkotlin.util.MLogUtils
import java.util.*

class MApplication : Application() {
    val mActivityStack = Stack<Activity>()
    var mClassName: String? = null

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        mClassName = javaClass.simpleName
        initLogger()
        registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
            override fun onActivityPaused(activity: Activity?) {

            }

            override fun onActivityResumed(activity: Activity?) {

            }

            override fun onActivityStarted(activity: Activity?) {

            }

            override fun onActivityDestroyed(activity: Activity?) {
                removeActivityByClass(activity!!.javaClass)
                logE("onActivityDestroyed() mActivityStack.size===" + mActivityStack.size)
            }

            override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

            }

            override fun onActivityStopped(activity: Activity?) {

            }

            override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
                mActivityStack.add(activity)
                logE("onActivityCreated() mActivityStack.size===" + mActivityStack.size)
            }
        })
    }

    private fun initLogger() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    fun removeActivityByClass(cls: Class<Any>) {
        val size = mActivityStack.size
        if (size == 0) return
        // 如果是栈顶,直接出栈
        if (mActivityStack.peek().javaClass.canonicalName == cls.canonicalName) {
            mActivityStack.pop()
            return
        }
        // 查找元素
        var index: Int = -1
        for (i in 0 until size) {
            if (mActivityStack[i].javaClass.canonicalName == cls.canonicalName) {
                index = i
                break
            }
        }
        if (index != -1) {
            mActivityStack.removeAt(index)
        }
    }

    fun finishActivityByClass(cls: Class<Any>) {
        val size = mActivityStack.size
        if (size == 0) return
        // 如果是栈顶,直接出栈
        if (mActivityStack.peek().javaClass.canonicalName == cls.canonicalName) {
            mActivityStack.peek().finish()
            return
        }
        // 查找元素
        var index: Int = -1
        for (i in 0 until size) {
            if (mActivityStack[i].javaClass.canonicalName == cls.canonicalName) {
                index = i
                break
            }
        }
        if (index != -1) {
            mActivityStack[index].finish()
        }
    }

    fun getNeedActivityByClass(cls: Class<Any>): Activity? {
        val size = mActivityStack.size
        if (size == 0) return null
        // 如果是栈顶,直接出栈
        if (mActivityStack.peek().javaClass.canonicalName == cls.canonicalName) {
            return mActivityStack.peek()
        }
        // 查找元素
        var index: Int = -1
        for (i in 0 until size) {
            if (mActivityStack[i].javaClass.canonicalName == cls.canonicalName) {
                index = i
                break
            }
        }
        if (index != -1) {
            return mActivityStack[index]
        }
        return null
    }

    fun keepActivityByClass(cls: Class<Any>) {
        val size = mActivityStack.size
        if (size == 0) return
        val list = mutableListOf<Activity>()
        for (i in 0 until size) {
            if (mActivityStack[i].javaClass.canonicalName != cls.canonicalName) {
                list.add(mActivityStack[i])
            }
        }
        for (activity in list) {
            mActivityStack.remove(activity)
        }
    }

    fun logD(obj: String) {
        logD(mClassName!!, obj)
    }

    fun logD(tag: String, obj: String) {
        MLogUtils.d(tag, obj)
    }

    fun logE(obj: String) {
        logE(mClassName!!, obj)
    }

    fun logE(tag: String, obj: String) {
        MLogUtils.e(tag, obj)
    }
}