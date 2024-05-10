package com.lw.kotlin

class KotlinLearn {


}

fun main() {
//    val a = null
//    a?.let {
//        println("not null")
//    } ?: println("1 null")
    val byteArray = byteArrayOf(-86, 1, -63, 113, 14, 28, 1, -94)
    val toHexString = String.format("%02X", byteArray[2])
    val stringBuilderTemp = StringBuilder()

    stringBuilderTemp.append(Integer.toHexString(byteArray[4].toInt()))
    stringBuilderTemp.append(Integer.toHexString(byteArray[5].toInt()))
    println(toHexString)
    println(stringBuilderTemp.toString())
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