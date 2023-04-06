package com.lw.wanandroid.performance.sdktask

import android.content.Context
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.task.ITask

class TaskTts : BStartUpTask() {
    override fun initYourSdkHere(appContext: Context): Boolean {
        println("BStartUp :  TaskTts.init")
        return true
    }

    override fun setYourTaskDependencies(): List<Class<out ITask>>? {
        return listOf(TaskMMKV::class.java, TaskRoom::class.java)
    }

}