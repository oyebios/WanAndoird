package com.lw.b.startup.concurrence


interface Dispatcher {
    /**
     * 是否在主线程调用
     */
    fun isCallOnMainThread(): Boolean

    /**
     * 通知前置条件完成了一个
     */
    fun notifyCountDown()

    /**
     * 等待
     */
    fun toWaitCountDown()
}