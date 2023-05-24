package aaa.lw.blog.main

import aaa.lw.blog.main.base.ILog
import aaa.lw.blog.main.logimpl.LogcatLog

object BLog {

    val logCatImpl: ILog by lazy { BLogFactory.getLoger() }
    val logFileImpl: ILog by lazy { BLogFactory.getLoger() }

    fun LogCat(): ILog {
        return logCatImpl
    }

    fun LogFile(): ILog {
        return logFileImpl
    }


    class Builder {

    }

    class BLogFactory {


        companion object {
            fun getLoger(): ILog {
                return LogcatLog()
            }
        }
    }
}