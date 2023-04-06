package com.lw.b.startup.concurrence

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


object BStartUpExecuter {
    //        //获得CPU核心数
//        private val CPU_COUNT = Runtime.getRuntime().availableProcessors()
//        private val CORE_POOL_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 5))
//        private val MAX_POOL_SIZE = CORE_POOL_SIZE
//        private val KEEP_ALIVE_TIME = 5L
//        var cpuExecutor: ThreadPoolExecutor =  ThreadPoolExecutor (CORE_POOL_SIZE, MAX_POOL_SIZE,
//            KEEP_ALIVE_TIME, TimeUnit.SECONDS, LinkedBlockingDeque < Runnable >(), Executors.defaultThreadFactory())
//        cpuExecutor.allowCoreThreadTimeOut(true)
    var ioExecutor = Executors.newCachedThreadPool(Executors.defaultThreadFactory())
    var mainExecutor = Executor {
        var handler = Handler(Looper.getMainLooper())
        handler.post(it)
    }
}