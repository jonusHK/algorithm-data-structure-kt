package greedy

import java.util.PriorityQueue


fun main(args: Array<String>) {
    val n = readln().toInt()

    val mapping = mutableListOf<Pair<Int, Int>>()

    repeat(n) {
        val (d, c) = readln().split(" ").map { it.toInt() }
        mapping.add(d to c)
    }
    mapping.sortBy { it.first }

    val queue = PriorityQueue<Int>()

    mapping.forEach {
        queue.add(it.second)
        if (queue.size > it.first) {
            queue.poll()
        }
    }

    println(queue.sum())
}