package dijkstra

import java.lang.Integer.max
import java.util.PriorityQueue

/* 프로그래머스 - 가장 먼 노드 */

var maxValue = 0

fun makeMapping(edge: Array<IntArray>): HashMap<Int, ArrayList<Int>> {
    val m = hashMapOf<Int, ArrayList<Int>>()

    edge.forEach { e ->
        m[e[0]] = m[e[0]] ?: arrayListOf()
        m[e[1]] = m[e[1]] ?: arrayListOf()
        m[e[0]]!!.add(e[1])
        m[e[1]]!!.add(e[0])
    }
    return m
}

fun dijkstra(q: PriorityQueue<Pair<Int, Int>>, d: Array<Int>, m: HashMap<Int, ArrayList<Int>>) {
    while (q.isNotEmpty()) {
        val (currNum, distance) = q.poll()
        if (d[currNum] < distance) {
            continue
        }
        for (nextNum in m[currNum]!!) {
            val nextDistance = 1 + distance
            if (d[nextNum] > nextDistance) {
                maxValue = max(nextDistance, maxValue)
                d[nextNum] = nextDistance
                q.add(nextNum to nextDistance)
            }
        }
    }
}

fun solution(n: Int, edge: Array<IntArray>): Int {
    val m = makeMapping(edge)
    val d = Array(n + 1) { Int.MAX_VALUE }
    val q = PriorityQueue<Pair<Int, Int>>(compareBy { it.second })
    val start = 1
    var result = 0

    d[start] = 0
    q.add(start to d[start])

    dijkstra(q, d, m)

    d.forEach {
        if (it == maxValue) {
            result++
        }
    }
    return result
}


fun main(args: Array<String>) {
    val n = 6
    val vertex = arrayOf(
        intArrayOf(3, 6),
        intArrayOf(4, 3),
        intArrayOf(3, 2),
        intArrayOf(1, 3),
        intArrayOf(1, 2),
        intArrayOf(2, 4),
        intArrayOf(5, 2)
    )
    println(solution(n, vertex))
}
