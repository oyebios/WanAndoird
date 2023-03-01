package com.lw.kotlin.OperatorOverloading.incdec


class Countdown(var timeLeft: Int) {
    operator fun inc(): Countdown {
        timeLeft += 1
        return this
    }

    operator fun dec(): Countdown {
        timeLeft -= 1
        return this
    }


    override fun toString(): String {
        return "还剩$timeLeft 秒"
    }
}

fun main() {
    var countdown = Countdown(10)
    println(countdown)
    println(countdown--)
//    println(--countdown)
    //inc 跟dec 会将值赋给原引用，不会改变对象
//    val countdown1 = countdown++
//    println(countdown1==countdown)
//    println(countdown1===countdown)

}