package com.lw.bapm.timemonitor.flow

import com.lw.bapm.timemonitor.config.TimeFlowConfig

class TimeFlow : ITimeFlow {

    constructor()


    constructor(config: TimeFlowConfig) : super() {
        mConfig = config
    }

    var mConfig: TimeFlowConfig = TimeFlowConfig()
    var mTimeStart: Long = 0L
    var mTimeNow: Long = 0L
    var mTimeLast: Long = 0L

    /**
     * status 0→init   1→started  2→steped  3→stoped
     */
    companion object {
        const val FLOW_STATUS_INIT = 0;
        const val FLOW_STATUS_STARTED = 1;
        const val FLOW_STATUS_STEPED = 2;
        const val FLOW_STATUS_STOPED = 3;

    }

    var mStatus = FLOW_STATUS_INIT

    override fun start(msg: String?) {
        /*if (mStatus == FLOW_STATUS_INIT){
            mStatus = FLOW_STATUS_STARTED
        }else if (mStatus > FLOW_STATUS_INIT&&!mConfig.restartWithStart){
            mStatus = FLOW_STATUS_STARTED
        }*/
        mTimeNow = System.currentTimeMillis()
        mTimeStart = mTimeNow
        mTimeLast = mTimeNow
        mStatus = FLOW_STATUS_STARTED
        logFlow(mStatus, msg)
    }

    override fun step(msg: String?) {
        mTimeLast = mTimeNow
        mTimeNow = System.currentTimeMillis()
        mStatus = FLOW_STATUS_STEPED
        logFlow(mStatus, msg)
    }

    override fun stop(msg: String?) {
        mTimeLast = mTimeNow
        mTimeNow = System.currentTimeMillis()
        mStatus = FLOW_STATUS_STOPED
        logFlow(mStatus, msg)
    }

    protected fun logFlow(status: Int, msg: String?) {
        mConfig.let { c ->
            c.logPrinter?.run {
                print(
                    flowTag = c.flowTag,
                    flowId = c.flowId,
                    flowLevel = c.flowLevel,
                    status = status,
                    timeStart = mTimeStart,
                    timeNow = mTimeNow,
                    timeLast = mTimeLast,
                    msg = msg,
                    logToFile = c.logToFile,
                    fileName = c.fileName
                )
            }
        }
    }
}
