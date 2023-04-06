package com.lw.b.startup.task


/**
 * 初始化sdk抽象类，一个Task表示一个需要初始化的步骤
 */
interface ITask {
    /**
     * 开始初始化sdk，返回是否成功
     */
    fun initSDK(): Boolean

    /**
     * 前置任务
     */
    fun dependencies(): List<Class<out ITask>>?
}