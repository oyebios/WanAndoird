package com.lw.bapm.kale

import android.util.Log
import android.util.Printer

class FrameTracePrinter : Printer {
    override fun println(x: String?) {
        x?.let {
            if (x.contains("android.view.Choreographer${'$'}FrameHandler")) {
                Log.d("FrameTracePrinter", "${System.currentTimeMillis()}----$x")
            }
        }
    }
}