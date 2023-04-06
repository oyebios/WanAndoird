package com.lw.wanandroid.performance.sdktask

import android.content.Context
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.task.ITask

class TaskMainThread : BStartUpTask() {

    override fun initYourSdkHere(appContext: Context): Boolean {
        println("BStartUp :  TaskMainThread.init")
        return true
    }

    override fun setYourTaskDependencies(): List<Class<out ITask>>? {
        return listOf(TaskTts::class.java)
    }
}