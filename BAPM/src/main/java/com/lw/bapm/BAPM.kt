package com.lw.bapm

import android.app.Application
import android.content.Context
import com.lw.bapm.base.Monitor
import com.lw.bapm.lifecycle.ProcessUILifecycleOwner
import com.lw.bapm.listener.TimeMonitorListener
import com.lw.bapm.timemonitor.TimeMonitor

object BAPM {

    val timeMonitor = TimeMonitor()


    val monitors: HashSet<Monitor> = HashSet()

    var app: Application? = null

    /**
     * 初始化 在BAPMContentProvider中获取到appcontext
     *
     */
    internal fun create(context: Context?) {
        if (context is Application) {
            app = context
            timeMonitor.init(context, TimeMonitorListener())
            monitors.add(timeMonitor)
            ProcessUILifecycleOwner.init(context)
        }
    }

    fun addMonitor(monitor: Monitor): BAPM {
        if (app == null) {
            throw NullPointerException("init BAPM before add monitor!!")
        }
        monitors.add(monitor)
        return this
    }


    inline fun <reified T : Monitor> getMonitor(clazz: Class<T>): T? {
        val clazzName = clazz.name
        for (monitor in monitors) {
            if (monitor.javaClass.name.equals(clazzName)) {
                if (monitor is T)
                    return monitor
            }
        }
        return null
    }
}