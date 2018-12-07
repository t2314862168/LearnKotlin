package com.tangxb.learnkotlin.demo1

import java.io.File

class Parent() {
    private val name: String? = null

    fun sum(a: Int, b: Int) = a + b
    fun main(args: Array<String>) {
        println("First args ${args[0]}")
    }

    fun cases(obj: Any) {
        when (obj) {
            1 -> println("one")
            "Hello" -> println("Hello")
            is Long -> println("is Long")
            !is String -> println("!is String")
            else -> println("Unknown")
        }
        val files = File("Test").listFiles()
        println(files?.size ?: "File Empty")
    }

    fun testMap() {
        val map: MutableMap<String, Any> = mutableMapOf()
        map["a"] = 1F
        map["b"] = "Hello"
        map["b"] = Double
        for ((k, v) in map) {
            println("key==$k&&value==$v")
        }


    }
}