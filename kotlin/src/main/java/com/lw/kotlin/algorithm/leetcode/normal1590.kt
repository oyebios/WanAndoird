package com.lw.kotlin.algorithm.leetcode

/**
 * 前缀和
 */
fun main() {

//    println(minSubarray(intArrayOf(3,1,4,2),6))
//    println(minSubarray(intArrayOf(6,3,5,2),9))
//    println(minSubarray(intArrayOf(6,1,2),6))
//    println(minSubarray(intArrayOf(8,32,31,18,34,20,21,13,1,27,23,22,11,15,30,4,2),148))
//    println(minSubarray(intArrayOf(1000000000,1000000000,1000000000),3))
    println(
        minSubarray(
            intArrayOf(
                1000000000,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1,
                1
            ), 1000000000
        )
    )
}

fun minSubarray(nums: IntArray, p: Int): Int {
    if (nums.size == 1) return if (nums[0] % p == 0) 0 else -1
    val s = IntArray(nums.size)
    s[0] = nums[0] % p
    for (i in 1 until nums.size) {//1..9
        s[i] = (s[i - 1] + nums[i]) % p
    }

    //取余
    val remainder = s[nums.size - 1]
    if (remainder == 0) return 0

    for (i in 1 until nums.size) {//2
        for (j in 0 until nums.size - i + 1) {//2
            val sumJI = if (j == 0) s[j + i - 1] else s[j + i - 1] - s[j - 1]
            if ((sumJI + p) % p == remainder)
                return i
        }
    }
    return -1
}
