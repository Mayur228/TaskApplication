package com.theappmakerbuddy.taskapplication

import javax.inject.Inject

class MainActivityRepository @Inject constructor() {
    fun parseNumbers(input: String): List<Int> {
        return input.split(",").map { it.trim().toInt() }
    }

    fun getIntersection(lists: List<List<Int>>): List<Int> {
        return lists.reduce { acc, list -> acc.intersect(list).toList() }
    }

    fun getUnion(lists: List<List<Int>>): List<Int> {
        return lists.reduce { acc, list -> acc.union(list).toList() }
    }

    fun getMaxNumber(union: List<Int>): Int? {
        return union.maxOrNull()
    }
}