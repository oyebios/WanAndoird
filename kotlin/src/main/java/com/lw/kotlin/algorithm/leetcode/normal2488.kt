package com.lw.kotlin.algorithm.leetcode


fun main() {
    println(countSubarrays(intArrayOf(3, 2, 1, 4, 5), 4))
}

fun countSubarrays(nums: IntArray, k: Int): Int {
    var qianzui = IntArray(nums.size)
    var pk = -1
    for (i in nums.indices) {
        nums[i] = when {
            nums[i] == k -> {
                pk = i
                0
            }
            nums[i] > k -> {
                1
            }
            else -> -1
        }
        if (i == 0) {
            qianzui[i] = nums[i]
        } else
            qianzui[i] = qianzui[i - 1] + nums[i]
    }

    val map = mutableMapOf<Int, Int>()
    map.put(0, 1)
    var ans = 0
    for (i in qianzui.indices) {
        if (i < pk) {
            map.put(qianzui[i], map.getOrDefault(qianzui[i], 0) + 1)
        } else {
            ans += map.getOrDefault(qianzui[i], 0) + map.getOrDefault(qianzui[i] - 1, 0)
        }
    }
    return ans
}