package com.dhkpo.algorithm.dijkstra

import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue


fun removeEdges(node: Int, trace: MutableMap<Int, LinkedList<Int>>, checked: Array<BooleanArray>) {
    val parents: LinkedList<Int>? = trace[node]
    if (parents.isNullOrEmpty()) {
        return
    }

    for (p: Int in parents) {
        checked[p][node] = true
        removeEdges(p, trace, checked)
    }
}

fun dijkstra(
    s: Int,
    dist: MutableMap<Int, Int>,
    mapping: ArrayList<ArrayList<Pair<Int, Int>>>,
    trace: MutableMap<Int, LinkedList<Int>>,
    checked: Array<BooleanArray>
) {
    dist[s] = 0

    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first })
    queue.add(dist[s]!! to s)

    while (queue.isNotEmpty()) {
        val (currDist: Int, currValue: Int) = queue.poll()

        if (dist[currValue]!! < currDist) {
            continue
        }

        mapping[currValue].forEach { (nextDist: Int, nextValue: Int) ->
            if (!checked[currValue][nextValue]) {
                if (dist[currValue]!! + nextDist <= dist[nextValue]!!) {
                    if (dist[currValue]!! + nextDist == dist[nextValue]!!) {
                        trace[nextValue]!!.add(currValue)
                    } else {
                        dist[nextValue] = dist[currValue]!! + nextDist
                        trace[nextValue]!!.clear()
                        trace[nextValue]!!.add(currValue)
                    }
                    queue.add(dist[nextValue]!! to nextValue)
                }
            }
        }
    }
}


fun main(args: Array<String>) {
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val (n: Int, m: Int) = readln().split(" ").map { it.toInt() }
        if (n == 0 && m == 0) {
            break
        }
        val (s: Int, d: Int) = readln().split(" ").map { it.toInt() }

        val adj = Array(n) { ArrayList<Pair<Int, Int>>() }.toCollection(ArrayList())
        val trace = HashMap<Int, LinkedList<Int>>()
        val checked = Array(n) { _ -> BooleanArray(n) { false } }

        val distForwardShortest = HashMap<Int, Int>()
        val distAlmostShortest = HashMap<Int, Int>()

        val distanceMax = Integer.MAX_VALUE

        repeat(m) {
            val (u: Int, v: Int, p: Int) = readln().split(" ").map { it.toInt() }
            adj[u].add(p to v)
        }

        repeat(n) {
            distForwardShortest[it] = distanceMax
            distAlmostShortest[it] = distanceMax
            trace[it] = LinkedList<Int>()
        }

        dijkstra(s, distForwardShortest, adj, trace, checked)
        removeEdges(d, trace, checked)
        dijkstra(s, distAlmostShortest, adj, trace, checked)

        val almostShortest: Int = distAlmostShortest[d]!!
        val result: Int = if (almostShortest == distanceMax) -1 else almostShortest

        bw.write(result.toString() + "\n")
    }
    bw.flush()
    bw.close()
}
