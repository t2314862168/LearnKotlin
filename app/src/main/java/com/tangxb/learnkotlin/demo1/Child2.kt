package com.tangxb.learnkotlin.demo1

import android.util.Log
import kotlinx.coroutines.experimental.*

class Child2 : Parent2(), Inter2 {
    override fun doInter1() {
        println("doInter1====")
    }

    override fun getLayoutResId() {
        println("getLayoutResId====")
    }

    fun doDemo1() {
        doInter1()
        getLayoutResId()
    }

    suspend fun doDemo2() {
        val job = launch(Unconfined) {
            withTimeout(1000) {
                try {
                    println("try at coroutine")
                } finally {
                    delay(2000)
                    println("finally at coroutine")
                }
            }
        }
        println("I am mainThread")
        Thread.sleep(2000)
    }

    fun testAsync() {
        //开启两个异步任务；这里只能用async，因为只有async有await()获取结果，并且异步
        val task1 = async {
            repeat(1) {
                Log.d("Task1", "当前线程：${Thread.currentThread().name}")
            }
            // return sth
            "AsyncTask1"
        }
        val task2 = async {
            repeat(1) {
                Log.d("Task2", "当前线程：${Thread.currentThread().name}")
            }
            // return sth
            "AsyncTask2"
        }
        //更新UI或async
        launch(Unconfined) {
            Log.d("UI1", "当前线程：${Thread.currentThread().name}")
            //当前UI线程的协程阻塞，但是不会使UI阻塞
//            Log.d("UI1", "task1.await()：${task1.await()}")
//            Log.d("UI1", "task2.await()：${task2.await()}")
            Log.d("UI2", "当前线程：${Thread.currentThread().name}")
        }
    }
}