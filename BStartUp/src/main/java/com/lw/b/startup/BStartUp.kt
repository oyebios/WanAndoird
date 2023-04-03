package com.lw.b.startup

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import com.lw.b.startup.cp.BStartUpContentProvider
import com.lw.b.startup.task.InitTask
import com.lw.b.startup.topo.TopoNode
import com.lw.b.startup.topo.sortByTopo


class BStartUp {

    object Inner {
        val INSTANCE = BStartUp()
    }

    companion object {
        val TAG = "BStartUp"
        fun getInstance() = Inner.INSTANCE
    }


    private val taskMap: HashMap<String, InitTask?> = hashMapOf()

    //开始初始化
    fun startUpWithXml(context: Context?) {
        context?.let { app ->
            //1.get all task names from xml
            val taskXmlSize = getTaskNamesFromXml(app)
            if (taskXmlSize == 0) return

            //2.create class
            for (className in taskMap.keys) {
                try {
                    //create class
                    val cls = Class.forName(className)
                    val newInstance = cls.getConstructor().newInstance() as InitTask
                    taskMap[className] = newInstance
                    Log.d(TAG, "class added:   $className")
                } catch (e: Exception) {
                    taskMap.remove(className)
                    Log.e(TAG, "class removed:   $className")
                }
            }

            if (taskMap.size == 0) return
            //3.sort by topo
            //get treenode
            val listNode = mutableListOf<TopoNode<InitTask>>()
            taskMap.forEach { task ->
                task.value?.run {
                    dependencies()?.let { depen ->
                        depen.forEach {
                            listNode.add(TopoNode(taskMap[it.name], this))
                        }
                    } ?: listNode.add(TopoNode(null, this))
                }
            }
            //sort
            val sortByTopo = sortByTopo(listNode)

            //do start
            sortByTopo.forEach {
//                val initTask = taskMap[it.name]
//                initTask?.init()
                Log.d(TAG, it.javaClass.simpleName)
            }

        } ?: Log.e(TAG, "app context can not be null!")
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