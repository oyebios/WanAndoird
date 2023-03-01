package com.lw.kotlin.OperatorOverloading.normal


class ListString(string: String) : Index<String> {
    var charArray = charArrayOf()
    var mSize = charArray.size

    init {
        charArray = string.toCharArray()
    }

    //父类无需再写operator关键字
    override fun get(int: Int, int2: Int): String? {
        return charArray[int].toString()
    }

    override fun invoke() {
        println("被调用拉")
    }

    override fun unaryPlus(): Int {
        return +charArray.size
    }

    override fun unaryMinus(): Int {
        return -charArray.size
    }

}

fun main() {
    var liststr = ListString("我是一串字符串")
    //Should be replaced with indexing
    //提示应该替换为indexing，即 println(liststr[0,1])
    println("[]  get重载")
    println(liststr.get(0, 1))
    println(liststr[0, 1])

    //下面两种写法效果相同
    println("()  directcall重载")
    liststr()

    //正负号重载
    println("+  -  正负号重载")
    println(+liststr)
    println(-liststr)

}