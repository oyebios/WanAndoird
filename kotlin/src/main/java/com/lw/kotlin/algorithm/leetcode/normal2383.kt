package com.lw.kotlin.algorithm.leetcode

fun main() {
//   println(minNumberOfHours(5,3, intArrayOf(1,4,3,2), intArrayOf(2,6,3,1)))

    println(minNumberOfHours(1, 1, intArrayOf(1, 1), intArrayOf(9, 1)))
}

fun minNumberOfHours(
    initialEnergy: Int,
    initialExperience: Int,
    energy: IntArray,
    experience: IntArray
): Int {
    val sumEnergy = energy.sum()
    val neededEnergy = if (initialEnergy > sumEnergy) 0 else sumEnergy - initialEnergy + 1
    println("neededEnergy$neededEnergy")
    var needExperience = 0
    if (experience[0] >= initialExperience) {
        needExperience = experience[0] - initialExperience + 1
    }
    if (experience.size == 1) {
        return needExperience + neededEnergy
    }
    //前缀和   s[i]+init +needed> ex[i+1]
    val s = IntArray(experience.size)
    s[0] = experience[0]
    for (i in 1 until experience.size) {
        s[i] = s[i - 1] + experience[i]
        if (experience[i] >= s[i - 1] + initialExperience + needExperience) {
            needExperience = experience[i] - initialExperience - s[i - 1] + 1
        }
    }
    println("needExperience$needExperience")
    return needExperience + neededEnergy
}