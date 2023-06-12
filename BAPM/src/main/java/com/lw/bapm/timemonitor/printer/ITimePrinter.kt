package com.lw.bapm.timemonitor.printer

interface ITimePrinter {
    fun print(
        flowTag: String,
        flowId: String,
        flowLevel: Int,
        status: Int,
        msg: String?,
        timeStart: Long,
        timeNow: Long,
        timeLast: Long,
        logToFile: Boolean,
        fileName: String?
    )
}