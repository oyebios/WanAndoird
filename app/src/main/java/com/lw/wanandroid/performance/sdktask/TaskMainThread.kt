package com.lw.wanandroid.performance.sdktask

import com.lw.b.startup.task.InitTask

class TaskMainThread : InitTask {
    override fun init(): Boolean {
        println("BStartUp :  TaskMainThread.init")
        return true
    }

    override fun createTask() {
    }

    override fun dependencies(): List<Class<out InitTask>> {
        return listOf(TaskTts::class.java)
    }

    override fun setPrediction(countDown: Int) {
    }

}