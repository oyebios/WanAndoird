package com.lw.bapm

import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.app.Instrumentation
import android.content.Context
import android.os.Bundle
import com.lw.bapm.listener.TimeMonitorListener
import com.lw.bapm.timemonitor.TimeMonitor
import java.lang.reflect.Method

object BAPM {

    val timeMonitor = TimeMonitor()
    var activityStartedCount = 0

    /**
     * 初始化 在BAPMContentProvider中获取到appcontext
     *
     */
    fun create(context: Context?) {
        if (context is Application) {
            timeMonitor.init(context, TimeMonitorListener())
            context.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    activityStartedCount += 1
                    timeMonitor.startUpFlow.step("the $activityStartedCount activity onActivityCreated")
                }

                override fun onActivityStarted(activity: Activity) {
                }

                override fun onActivityResumed(activity: Activity) {

                    timeMonitor.startUpFlow.step("the $activityStartedCount activity resumed")
                }

                override fun onActivityPaused(activity: Activity) {
                }

                override fun onActivityStopped(activity: Activity) {
                }

                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
                }

                override fun onActivityDestroyed(activity: Activity) {
                }
            })
        }
    }

    private fun hookActivityInstrumentation(): Instrumentation? {
        try {
            val activityThreadClass = Class.forName("android.app.ActivityThread")
            val currentActivityThreadMethod: Method =
                activityThreadClass.getDeclaredMethod("currentActivityThread")
            currentActivityThreadMethod.setAccessible(true)
            val currentActivityThread: Any = currentActivityThreadMethod.invoke(null)
            val getInstrumentationMethod: Method =
                activityThreadClass.getDeclaredMethod("getInstrumentation")
            getInstrumentationMethod.setAccessible(true)
            val instrumentation: Any = getInstrumentationMethod.invoke(currentActivityThread)
            return (instrumentation as Instrumentation)
            // Use the instrumentation object as needed
        } catch (e: Exception) {
            // Handle any exceptions
            return null
        }
    }

}