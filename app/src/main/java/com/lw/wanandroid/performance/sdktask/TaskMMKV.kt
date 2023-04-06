package com.lw.wanandroid.performance.sdktask

import android.content.Context
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.task.ITask

class TaskMMKV : BStartUpTask() {
    override fun initYourSdkHere(appContext: Context): Boolean {
        println("BStartUp :  mmkv.init")
        return true
    }

    override fun setYourTaskDependencies(): List<Class<out ITask>>? {
        return listOf(TaskBaseUtils::class.java)
    }

}