package com.lw.kotlin.algorithm.leetcode

/**
 *
 * dp算法   f(i,j) = max[f(i,j-1),f(i-1,j)]+grid[i,j]
 * leetcode 47[[1,2,5],[3,2,1]]
 */
fun main() {
    val grid = arrayOf(
        intArrayOf(1, 2),
        intArrayOf(5, 5),
        intArrayOf(5, 5)
    )
    println(maxValue(grid))
}

fun maxValue(grid: Array<IntArray>): Int {
    val v = grid.size
    val h = grid[0].size
    for (f in 1 until v)
        grid[f][0] += grid[f - 1][0]

    for (f in 1 until h)
        grid[0][f] += grid[0][f - 1]

    for (i in 1 until v) {
        for (j in 1 until h) {
            grid[i][j] += if (grid[i - 1][j] > grid[i][j - 1]) grid[i - 1][j] else grid[i][j - 1]
        }
    }

    return grid[grid.size - 1][grid[0].size - 1]
}