package com.lw.wanandroid.performance.sdktask

import android.content.Context
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.task.ITask

class TaskBaseUtils : BStartUpTask() {

    override fun initYourSdkHere(appContext: Context): Boolean {
        println("BStartUp :  TaskBaseUtils.init")
        return true
    }

    override fun setYourTaskDependencies(): List<Class<out ITask>>? {
        return null
    }
}