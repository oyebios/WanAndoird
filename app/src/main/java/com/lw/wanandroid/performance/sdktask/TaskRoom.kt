package com.lw.wanandroid.performance.sdktask

import com.lw.b.startup.task.InitTask

class TaskRoom : InitTask {
    override fun init(): Boolean {
        println("BStartUp :  TaskRoom.init")
        return true
    }

    override fun createTask() {

    }

    override fun dependencies(): List<Class<out InitTask>>? {
        return listOf(TaskBaseUtils::class.java)
    }

    override fun setPrediction(countDown: Int) {

    }


}