package com.lw.kotlin.OperatorOverloading.normal

interface Index<T> {
    //重载[]→get()   入参至少有一个，可以多个
    operator fun get(int: Int, int2: Int): T?

    //重载()→invoke()   入参可为空，可以多个
    operator fun invoke()

    //重载正号+  入参可为空，可以多个
    operator fun unaryPlus(): Int

    //重载负号- 入参可为空，可以多个
    operator fun unaryMinus(): Int

}
