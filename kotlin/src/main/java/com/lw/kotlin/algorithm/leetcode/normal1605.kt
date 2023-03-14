package com.lw.kotlin.algorithm.leetcode

fun main() {

    for (restoreMatrix in restoreMatrix(intArrayOf(3, 8), intArrayOf(4, 7))) {
        for (i in restoreMatrix) {
            print("$i   ")
        }
        println()
    }
}

fun restoreMatrix(rowSum: IntArray, colSum: IntArray): Array<IntArray> {
    val result = Array(rowSum.size) { IntArray(colSum.size) }
    for (i in rowSum.indices) {
        for (j in colSum.indices) {
            result[i][j] = if (rowSum[i] > colSum[j]) colSum[j] else rowSum[i]
            rowSum[i] -= result[i][j]
            colSum[j] -= result[i][j]
        }
    }
    return result
}