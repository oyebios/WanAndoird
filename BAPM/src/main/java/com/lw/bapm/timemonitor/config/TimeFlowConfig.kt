package com.lw.bapm.timemonitor.config

import android.util.Log
import com.lw.bapm.timemonitor.printer.ITimePrinter
import com.lw.bapm.timemonitor.printer.LogTimePrinter

/**
 * @param flowTag 一个流程的tag
 * @param flowId 具体的流程Id
 * @param restartWithStart 在未结束的情况下启动start是否重启
 * @param logToFile 是否输出到文件中
 * @param fileName 文件名
 */
data class TimeFlowConfig(
    var flowTag: String = DEFAULT_FLOW_LOG_TAG,
    var flowId: String = DEFAULT_FLOW_ID,
    var flowLevel: Int = Log.DEBUG,
//                          var restartWithStart : Boolean = DEFAULT_FLOW_START_RULE,
    var logToFile: Boolean = DEFAULT_FLOW_LOG_TO_FILE,
    var fileName: String? = DEFAULT_FLOW_LOG_FILE_NAME,
    var logPrinter: ITimePrinter? = LogTimePrinter()
)


const val DEFAULT_FLOW_LOG_TAG = "TimeFlowTag"

const val DEFAULT_FLOW_ID = "TimeFlowId"

//const val DEFAULT_FLOW_START_RULE = true

const val DEFAULT_FLOW_LOG_TO_FILE = false

const val DEFAULT_FLOW_LOG_FILE_NAME = "TimeMonitorLog"
