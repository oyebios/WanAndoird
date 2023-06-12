package com.lw.bapm.timemonitor.printer

import android.util.Log
import com.lw.bapm.timemonitor.flow.TimeFlow.Companion.FLOW_STATUS_STARTED
import com.lw.bapm.timemonitor.flow.TimeFlow.Companion.FLOW_STATUS_STEPED
import com.lw.bapm.timemonitor.flow.TimeFlow.Companion.FLOW_STATUS_STOPED

class LogTimePrinter : ITimePrinter {
    override fun print(
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
    ) {
        val stringBuilder = StringBuilder()
        stringBuilder
            .append("FlowTag : $flowTag---------------------")
            .appendLine()
            .append("FlowId : $flowId")
            .appendLine()
            .append("tid : ${Thread.currentThread()}")
            .appendLine()
            .append("FlowStatus : ${getStatusString(status)}")
            .appendLine()
            .append("$msg")
            .appendLine()
            .append("time cost from start ${timeNow - timeStart}")
            .appendLine()
            .append("time cost from last step ${timeNow - timeLast}")
            .appendLine()
            .append("---------------end--------------")
        Log.println(flowLevel, flowTag, stringBuilder.toString())
    }

    fun getStatusString(status: Int): String {
        return when (status) {
            FLOW_STATUS_STARTED -> "start"
            FLOW_STATUS_STEPED -> "step"
            FLOW_STATUS_STOPED -> "stop"
            else -> "init"

        }
    }

}