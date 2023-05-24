package aaa.lw.blog.main.logimpl

import aaa.lw.blog.main.base.ILog
import android.util.Log

class LogcatLog : ILog {
    override fun d(tag: String, msg: String) {
        log(Log.DEBUG, tag, msg)
    }

    override fun e(tag: String, msg: String) {
        log(Log.ERROR, tag, msg)
    }

    override fun e(tag: String, msg: String, throwable: Throwable) {
        log(Log.ERROR, tag, "$msg\n${Log.getStackTraceString(throwable)}")
    }

    override fun flow(tag: String, method: String, msg: String) {
        log(Log.DEBUG, tag, "$method\n$msg")
    }

    private fun log(priority: Int, tag: String, msg: String) {
        Log.println(priority, tag, msg)
    }

}