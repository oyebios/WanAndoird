package com.lw.kotlin.keyword

import kotlin.reflect.KProperty


fun main() {
    var user1: String? by Delegator()
    println(user1)
}

fun String.exstrin() {
    println("11")
}

class Delegator {

    operator fun getValue(person: String?, property: KProperty<*>): String? {
        return person ?: "empty"
    }

    operator fun setValue(person: String?, property: KProperty<*>, s: String?) {
        println("setvalue")
    }


}