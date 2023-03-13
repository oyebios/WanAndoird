package com.lw.kotlin.map


fun main() {
    val a = arrayOf("111", "22")
    val map2 = a.flatMap {
        it.asIterable()
    }
    println(map2)
}