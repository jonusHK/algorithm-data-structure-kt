/* 프로그래머스 - 피로도 */

package bp.`1`

import java.lang.Integer.max

var maxCount = 0

fun dfs(k: Int, idx: Int, dungeons: Array<IntArray>, count: Int, visited: HashSet<Int>) {
    if (k < dungeons[idx][0]) {
        return
    }

    val newK = k - dungeons[idx][1]
    val newCnt = count + 1
    val newVisited = visited.toHashSet()

    maxCount = max(newCnt, maxCount)
    newVisited.add(idx)

    for (i in dungeons.indices) {
        if (i !in newVisited) {
            dfs(newK, i, dungeons, count + 1, newVisited)
        }
    }
}

fun solution(k: Int, dungeons: Array<IntArray>): Int {
    for (i in dungeons.indices) {
        dfs(k, i, dungeons, 0, hashSetOf())
    }
    return maxCount
}

fun main(args: Array<String>) {
    val k = 80
    val dungeons = arrayOf(
        intArrayOf(80, 20),
        intArrayOf(50, 40),
        intArrayOf(30, 10)
    )
    println(solution(k, dungeons))
}
