package com.lw.bapm.base

import android.app.Application

/**
 * Android Performance Monitor Base Interface
 *
 */
interface IMonitor {

    fun init(app: Application, listener: IMonitorListener)

    fun start()

    fun stop()

    fun destory()

    fun onForground(isForground: Boolean)
}