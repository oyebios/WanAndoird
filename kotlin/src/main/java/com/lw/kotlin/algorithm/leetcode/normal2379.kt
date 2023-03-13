package com.lw.kotlin.algorithm.leetcode


/**
 * 滑动窗口
 */
fun main() {

    println(minimumRecolors("WBBWWBBWBW", 10))

}

fun minimumRecolors(blocks: String, k: Int): Int {
    if (!blocks.contains('W'))
        return 0
    var min = 0
    for (i in 0 until k) {
        if (blocks[i] == 'W')
            min += 1
    }
    if (k < blocks.length) {
        var current = min
        for (i in 1..blocks.length - k) {
            if (blocks[i - 1] == 'W') {
                current -= 1
            }
            if (blocks[i + k - 1] == 'W') {
                current += 1
            }
            min = if (min > current) current else min
        }
    }
    return min
}