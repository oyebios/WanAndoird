package com.lw.bapm.base

interface IMonitorListener {
    fun onInit(monitor: IMonitor)

    fun onStart(monitor: IMonitor)

    fun onStop(monitor: IMonitor)

    fun onDestory(monitor: IMonitor)

    fun onForground(monitor: IMonitor, isForground: Boolean)
}
