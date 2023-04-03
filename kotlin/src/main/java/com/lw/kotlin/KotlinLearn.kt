package com.lw.kotlin

class KotlinLearn {


}

fun main() {
    val a = null
    a?.let {
        println("not null")
    } ?: println("1 null")
}

fun test() {
    var time = System.currentTimeMillis()
//    for (i in 0..10000){
//        try {
//            val cls = Class.forName("com.lw.kotlin.KotlinLearn")
//            val newInstance = cls.getConstructor().newInstance()
//        }catch (e : Exception){
//
//        }
//    }
    try {
        for (i in 0..10000) {
            val cls = Class.forName("com.lw.kotlin.KotlinLearn")
            val newInstance = cls.getConstructor().newInstance()
        }
    } catch (e: Exception) {

    }

    time = System.currentTimeMillis() - time
    println(time)
}