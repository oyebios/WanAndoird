package com.lw.b.startup.task


/**
 * 初始化sdk抽象类，一个Task表示一个需要初始化的步骤
 */
interface InitTask {
    /**
     * 开始初始化sdk，返回是否成功
     */
    fun init(): Boolean


    /**
     *初始化task
     */
    fun createTask()

    /**
     * 前置任务
     */
    fun dependencies(): List<Class<out InitTask>>?

    /**
     * 设置前置task数量
     */
    fun setPrediction(countDown: Int)
}