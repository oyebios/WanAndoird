package com.lw.bapm.timemonitor.flow

interface ITimeFlow {
    fun start(msg: String?)
    fun step(msg: String?)
    fun stop(msg: String?)
}