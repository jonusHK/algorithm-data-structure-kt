package dp

import kotlin.math.min
import kotlin.math.abs


private fun copyHashMap(map: HashMap<Int, HashSet<Int>>): HashMap<Int, HashSet<Int>> {
    val copy = hashMapOf<Int, HashSet<Int>>()
    for ((k, v) in map) {
        copy[k] = v.toHashSet()
    }
    return copy
}
private fun initMap(wires: Array<IntArray>): HashMap<Int, HashSet<Int>> {
    val map = hashMapOf<Int, HashSet<Int>>()
    wires.forEach {
        map[it[0]] = map[it[0]] ?: hashSetOf()
        map[it[1]] = map[it[1]] ?: hashSetOf()
        map[it[0]]!!.add(it[1])
        map[it[1]]!!.add(it[0])
    }
    return map
}

private fun combination(arr: Array<Int>, n: Int, r: Int): ArrayList<Array<Int>> {
    val newArr = arrayListOf<Array<Int>>()

    fun recursive(depth: Int = 0, index: Int = 0, resultArray: Array<Int> = arr.sliceArray(0 until r)) {
        if (depth == r) {
            newArr.add(resultArray.toList().toTypedArray())
            return
        }

        for (i in index until n) {
            resultArray[depth] = arr[i]
            recursive(depth + 1, i + 1, resultArray)
        }
    }

    recursive()
    return newArr
}

private fun validateRemoveTarget(map: HashMap<Int, HashSet<Int>>, target: Array<Int>): Boolean {
    return target[1] in map[target[0]]!!
}

private fun removeTarget(map: HashMap<Int, HashSet<Int>>, target: Array<Int>) {
    map[target[0]]!!.remove(target[1])
    map[target[1]]!!.remove(target[0])
}

private fun countConnections(map: HashMap<Int, HashSet<Int>>, start: Int, n: Int): Int {
    var connectionCnt = 0
    val q = LinkedHashSet<Int>()
    val visited = BooleanArray(n + 1)

    q.addAll(map[start]!!)
    visited[start] = true
    connectionCnt++

    while (q.isNotEmpty()) {
        val pop = q.first().also { q.remove(it) }
        connectionCnt++
        visited[pop] = true
        for (m in map[pop]!!) {
            if (!visited[m]) {
                q.add(m)
            }
        }
    }
    return connectionCnt
}

private fun solution(n: Int, wires: Array<IntArray>): Int {
    val map = initMap(wires)
    var minDiff = Int.MAX_VALUE

    for (target in combination((1..n).toList().toTypedArray(), n, 2)) {
        if (!validateRemoveTarget(map, target)) {
            continue
        }

        val copyMap = copyHashMap(map)
        removeTarget(copyMap, target)

        val countFirst = countConnections(copyMap, target[0], n)
        val countSecond = countConnections(copyMap, target[1], n)

        minDiff = min(minDiff, abs(countFirst - countSecond))
    }

    return minDiff
}

fun main(args: Array<String>) {
    val n = 9
    val wires = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(2, 3),
        intArrayOf(3, 4),
        intArrayOf(4, 5),
        intArrayOf(4, 6),
        intArrayOf(4, 7),
        intArrayOf(7, 8),
        intArrayOf(7, 9)
    )
    println(solution(n, wires))
}
