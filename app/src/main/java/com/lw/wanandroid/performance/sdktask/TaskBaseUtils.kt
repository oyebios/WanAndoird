package com.lw.wanandroid.performance.sdktask

import com.lw.b.startup.task.InitTask

class TaskBaseUtils : InitTask {
    override fun init(): Boolean {
        println("BStartUp :  TaskBaseUtils.init")
        return true
    }

    override fun createTask() {

    }

    override fun dependencies(): List<Class<out InitTask>>? {
        return null
    }

    override fun setPrediction(countDown: Int) {

    }


}