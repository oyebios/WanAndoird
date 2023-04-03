package com.lw.b.startup.topo

import android.util.Log
import com.lw.b.startup.BStartUp

/**
 * 拓扑排序，查找路径
 */
fun <T> sortByTopo(list: MutableList<TopoNode<T>>): List<T> {
    //入度表
    val inDegreeMap = mutableMapOf<T, Int>()
    val childMap = mutableMapOf<T, MutableList<T>>()
    //0度表
    val dueueZero = ArrayDeque<T>()
    val listResult = mutableListOf<T>()


    //初始化入度表
    for (topoNode in list) {
        if (topoNode.from == null) {

            Log.d(BStartUp.TAG, "sortByTopo Node:null → ${topoNode.to}")
            inDegreeMap[topoNode.to] = inDegreeMap[topoNode.to] ?: 0
        } else {

            Log.d(BStartUp.TAG, "sortByTopo Node:${topoNode.from} → ${topoNode.to}")
            inDegreeMap[topoNode.to] = inDegreeMap[topoNode.to]?.let { it + 1 } ?: 1
            val mutableList = childMap[topoNode.from!!]
            if (mutableList == null) {
                childMap[topoNode.from!!] = mutableListOf(topoNode.to)
            } else {
                mutableList.add(topoNode.to)
            }
        }
    }
    //初始化0度表
    inDegreeMap.forEach {
        if (it.value == 0) {
            dueueZero.add(it.key)
        }
    }

    while (!dueueZero.isEmpty()) {
        val removeLast = dueueZero.removeLast()
        listResult.add(removeLast)
        inDegreeMap.remove(removeLast)

        childMap[removeLast]?.forEach {
            val inDegree = inDegreeMap[it]
            inDegreeMap[it] = inDegree!! - 1
            if (inDegree == 1) {
                dueueZero.add(it)
            }
        }

    }

    return listResult

}