package com.lw.bapm.kale

import android.os.Looper
import android.view.Choreographer

/**
 * 监测卡顿
 * 1.给mainLooper添加logger，看每次msg处理的间隔以及时间
 * 2.监听choreographer
 */
class FrameTrace {
    /**
     * 监听handler的执行
     *
     */
    fun addHandlerLogger() {
        val mainLooper = Looper.getMainLooper()
        mainLooper.setMessageLogging(FrameTracePrinter())
    }

    /**
     * 编舞者添加callback
     */
    fun addChoreographerCallBack() {
        Choreographer.getInstance().postFrameCallback(FrameTraceCallBack())
    }
}