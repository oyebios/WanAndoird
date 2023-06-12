package com.lw.bapm.timemonitor

import android.app.Application
import com.lw.bapm.base.IMonitorListener
import com.lw.bapm.base.Monitor
import com.lw.bapm.timemonitor.flow.TimeFlow

/**
 *
 *
 */
class TimeMonitor : Monitor() {

    //    var timeFlowsMap = Hashtable
    val startUpFlow = TimeFlow()

    override fun init(app: Application, listener: IMonitorListener) {
        super.init(app, listener)
        startUpFlow.start("application context created")
    }


}