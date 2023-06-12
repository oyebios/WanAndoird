package com.lw.bapm.timemonitor.config

import com.lw.bapm.timemonitor.flow.TimeFlow
import com.lw.bapm.timemonitor.printer.ITimePrinter

class TimeFlowConfigBuilder {

    //default config
    var config: TimeFlowConfig = TimeFlowConfig()


    fun flowTag(tag: String): TimeFlowConfigBuilder {
        config.flowTag = tag
        return this
    }

    fun flowId(id: String): TimeFlowConfigBuilder {
        config.flowId = id
        return this
    }

    fun flowLevel(level: Int): TimeFlowConfigBuilder {
        config.flowLevel = level
        return this
    }

//    fun restartWithStart(restartWithStart : Boolean) : TimeFlowConfigBuilder {
//        config.restartWithStart = restartWithStart
//        return this
//    }

    fun logToFile(logtofile: Boolean): TimeFlowConfigBuilder {
        config.logToFile = logtofile
        return this
    }

    fun logFileName(fileName: String): TimeFlowConfigBuilder {
        config.fileName = fileName
        return this
    }

    fun logPrinter(logtofile: ITimePrinter): TimeFlowConfigBuilder {
        config.logPrinter = logtofile
        return this
    }


    fun build(): TimeFlowConfig {
        return config
    }

    fun buildFlow(): TimeFlow {
        return TimeFlow(config)
    }
}