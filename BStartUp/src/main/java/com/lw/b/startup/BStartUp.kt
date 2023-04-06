package com.lw.b.startup

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.lw.b.startup.concurrence.BStartUpExecuter
import com.lw.b.startup.cp.BStartUpContentProvider
import com.lw.b.startup.task.BStartUpTask
import com.lw.b.startup.topo.TopoNode
import com.lw.b.startup.topo.sortByTopo
import java.util.concurrent.CountDownLatch


class BStartUp {

    object Inner {
        val INSTANCE = BStartUp()
    }

    companion object {
        val TAG = "BStartUp"
        fun getInstance() = Inner.INSTANCE
    }


    private val taskMap: HashMap<String, BStartUpTask?> = hashMapOf()
    private val childMap: HashMap<String, MutableList<BStartUpTask>> = hashMapOf()
    private lateinit var mainThreadCountDown: CountDownLatch
    lateinit var appContext: Context

    //开始初始化
    fun startUpWithXml(context: Context?) {
        context?.let { app ->
            appContext = app
            //1.get all task names from xml
            val taskXmlSize = getTaskNamesFromXml(app)
            if (taskXmlSize == 0) return

            //2.create class
            for (className in taskMap.keys) {
                try {
                    //create class
                    val cls = Class.forName(className)
                    val newInstance = cls.getConstructor().newInstance() as BStartUpTask
                    taskMap[className] = newInstance
                    Log.d(TAG, "class added:   $className")
                } catch (e: Exception) {
                    taskMap.remove(className)
                    Log.e(TAG, "class removed:   $className")
                }
            }

            if (taskMap.size == 0) return
            //3.sort by topo
            //get treenode  and childmap
            val listNode = mutableListOf<TopoNode<BStartUpTask>>()
            var mainThreadTaskCount = 0
            taskMap.forEach { task ->
                task.value?.run {
                    if (isCallOnMainThread())
                        mainThreadTaskCount += 1
                    dependencies()?.let { depen ->
                        depen.forEach {
                            val mutableList = childMap[it.name]
                            if (mutableList == null) {
                                childMap[it.name] = mutableListOf(this)
                            } else {
                                mutableList.add(this)
                            }
                            listNode.add(TopoNode(taskMap[it.name], this))
                        }
                    } ?: listNode.add(TopoNode(null, this))
                }
            }
            mainThreadCountDown = CountDownLatch(mainThreadTaskCount)
            //sort
            val sortByTopo = sortByTopo(listNode)

            //do start
            sortByTopo.forEach { task ->
//                val initTask = taskMap[it.name]
//                initTask?.init()
                Log.d(TAG, task.javaClass.simpleName)
                //主线程调用
                if (task.isCallOnMainThread()) {
                    task.run()
                } else {
                    BStartUpExecuter.ioExecutor.execute(task)
                }
            }
            //主线程阻塞等待初始化完成
            mainThreadCountDown.await()

        } ?: Log.e(TAG, "app context can not be null!")
    }

    /**
     * 单个任务完成，通知
     */
    fun notifyChild(task: BStartUpTask) {
        if (task.isCallOnMainThread()) {
            mainThreadCountDown.countDown()
        }
        childMap[task.javaClass.name]?.forEach {
            it.notifyCountDown()
        }
    }

    /**
     * 从contentprovider配置的数据中读取需初始化的task
     */
    private fun getTaskNamesFromXml(app: Context): Int {
        try {
            val pm = app.packageManager
            val provider = ComponentName(app.packageName, BStartUpContentProvider::class.java.name)
            val providerInfo = pm.getProviderInfo(provider, PackageManager.GET_META_DATA)
            providerInfo
                .metaData
                .keySet()
                .forEach {
                    taskMap[it] = null
                }
        } catch (e: Exception) {
            Log.e(TAG, "Provider Not Found!")
            e.printStackTrace()
        }
        Log.d(TAG, "xml task size : ${taskMap.size}")
        return taskMap.size
    }

}