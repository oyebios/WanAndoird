package com.lw.b.startup.task

import android.content.Context
import com.lw.b.startup.BStartUp
import com.lw.b.startup.concurrence.Dispatcher
import java.util.concurrent.CountDownLatch


/**
 * 初始化sdk抽象类，一个Task表示一个需要初始化的步骤
 */
abstract class BStartUpTask : ITask, Dispatcher, Runnable {
    val countDown: CountDownLatch by lazy { CountDownLatch(dependencies()?.size ?: 0) }
    override fun isCallOnMainThread(): Boolean {
        return false
    }

    override fun notifyCountDown() {
        countDown.countDown()
    }

    override fun toWaitCountDown() {
        countDown.run { await() }
    }

    override fun initSDK(): Boolean {
        return initYourSdkHere(BStartUp.getInstance().appContext)
    }

    abstract fun initYourSdkHere(appContext: Context): Boolean

    override fun dependencies(): List<Class<out ITask>>? {
        return setYourTaskDependencies()
    }

    abstract fun setYourTaskDependencies(): List<Class<out ITask>>?

    override fun run() {
//        //若想要在主线程执行，但是不阻塞主线程，可以用handler处理，countdownlatch可以在子线程等待
//        if (isCallOnMainThread()){
//            var  handler =  Handler(Looper.getMainLooper())
//            handler.post(object :Runnable{
//                override fun run() {
//                    initSDK()
//                    BStartUp.getInstance().notifyChild(this@BStartUpTask)
//                }
//            })
//            return
//        }
        //先等待,当countdownlatch不为0，则会等待
        toWaitCountDown()
        initSDK()
        BStartUp.getInstance().notifyChild(this)
    }
}