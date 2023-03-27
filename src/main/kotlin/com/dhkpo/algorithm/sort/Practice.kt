package com.dhkpo.algorithm.sort

import java.util.PriorityQueue


fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }

    val orders: Array<MutableList<Int>> = Array(n + 1) { mutableListOf() }
    val indegree: Array<Int> = Array(n + 1) { 1 }
    val firstIndegree = 1

    repeat(m) {
        val (a, b) = readln().split(" ").map { it.toInt() }
        orders[a].add(b)
        indegree[b]++
    }

    val queue = PriorityQueue<Pair<Int, Int>>(compareBy({ it.first }, { it.second }))

    for (num in 1..n) {
        if (indegree[num] == firstIndegree) {
            queue.add(indegree[num] to num)
        }
    }

    val result = mutableListOf<Int>()

    while (queue.isNotEmpty()) {
        val (_, num) = queue.poll()
        result.add(num)
        if (orders[num].isNotEmpty()) {
            orders[num].forEach {
                indegree[it]--
                if (indegree[it] == firstIndegree) {
                    queue.add(indegree[it] to it)
                }
            }
        }
    }

    println(result.joinToString(" "))
}
