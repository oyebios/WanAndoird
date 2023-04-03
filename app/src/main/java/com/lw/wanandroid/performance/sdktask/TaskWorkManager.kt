package com.lw.wanandroid.performance.sdktask

import com.lw.b.startup.task.InitTask

class TaskWorkManager : InitTask {
    override fun init(): Boolean {
        println("BStartUp :  TaskWorkManager.init")
        return true
    }

    override fun createTask() {
    }

    override fun dependencies(): List<Class<out InitTask>> {
        return listOf(TaskMMKV::class.java, TaskRoom::class.java)
    }

    override fun setPrediction(countDown: Int) {
    }

}