package com.lw.kotlin.algorithm.leetcode

fun main() {

    answerQueries(intArrayOf(2, 3, 4, 5), intArrayOf(1)).forEach {
        println(it)
    }

}

fun answerQueries(nums: IntArray, queries: IntArray): IntArray {
    val ans = IntArray(queries.size) { 0 }
    nums.sort()
    val s = IntArray(nums.size)
    s[0] = nums[0]
    for (i in 1 until nums.size) {
        s[i] = s[i - 1] + nums[i]
    }
    for (i in queries.indices) {
        for (j in nums.size - 1 downTo 0) {
            if (s[j] <= queries[i]) {
                ans[i] = j + 1
                break
            }
        }
    }
    return ans
}