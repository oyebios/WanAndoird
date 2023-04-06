package com.lw.wanandroid.performance.sdktask

import android.content.Context
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.task.ITask

class TaskApm : BStartUpTask() {
    override fun initYourSdkHere(appContext: Context): Boolean {
        println("BStartUp :  TaskApm.init")
        return true
    }

    override fun setYourTaskDependencies(): List<Class<out ITask>>? {
        return null
    }


}