package com.lw.bapm.kale

import android.view.Choreographer

class FrameTraceCallBack : Choreographer.FrameCallback {
    override fun doFrame(frameTimeNanos: Long) {
        println(System.currentTimeMillis())
        Choreographer.getInstance().postFrameCallback(this)
    }
}