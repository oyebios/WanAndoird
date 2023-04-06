package com.lw.wanandroid.performance.sdktask

import android.content.Context
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.task.ITask

class TaskRoom : BStartUpTask() {

    override fun initYourSdkHere(appContext: Context): Boolean {
        println("BStartUp :  TaskRoom.init")
        return true
    }

    override fun setYourTaskDependencies(): List<Class<out ITask>>? {
        return listOf(TaskBaseUtils::class.java)
    }

}