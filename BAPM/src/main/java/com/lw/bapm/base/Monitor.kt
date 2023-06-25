package com.lw.bapm.base

import android.app.Application


abstract class Monitor : IMonitor {
    var appContext: Application? = null
    var monitorListener: IMonitorListener? = null

    enum class MonitorState {
        CREATE,
        INITED,
        STARTED,
        STOPED,
        DESTORYED
    }

    var state: MonitorState = MonitorState.CREATE

    override fun init(app: Application, listener: IMonitorListener) {
        if (appContext != null || monitorListener != null) {
            throw RuntimeException("monitor has already been initialized,duplicated monitor!!")
        }
        appContext = app
        monitorListener = listener
        monitorListener?.apply {
            state = MonitorState.INITED
            onInit(this@Monitor)
        } ?: throw NullPointerException("monitorlistener is null")
    }

    override fun start() {
        monitorListener?.apply {
            state = MonitorState.STARTED
            onStart(this@Monitor)
        } ?: throw NullPointerException("monitorlistener is null")
    }

    override fun stop() {
        monitorListener?.apply {
            state = MonitorState.STOPED
            onStop(this@Monitor)
        } ?: throw NullPointerException("monitorlistener is null")
    }

    override fun onForground(isForground: Boolean) {
    }

    override fun destory() {
        monitorListener?.apply {
            state = MonitorState.DESTORYED
            onDestory(this@Monitor)
        } ?: throw NullPointerException("monitorlistener is null")
        var appContext = null
        var monitorListener = null


    }


}