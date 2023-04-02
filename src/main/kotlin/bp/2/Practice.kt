package bp.`2`

import java.lang.Integer.max

val cases = arrayListOf<Array<IntArray>>()

fun permutation(count: Int, flag: Int, dungeons: Array<IntArray>, case: Array<IntArray>) {
    if (count == dungeons.size) {
        cases.add(case.copyOf())
        return
    }

    for (i in dungeons.indices) {
        if (flag and (1 shl i) != 0) {
            continue
        }
        case[count] = dungeons[i]
        permutation(count + 1, flag or (1 shl i), dungeons, case)
    }
}

fun initCases(dungeons: Array<IntArray>) {
    permutation(0, 0, dungeons, Array(dungeons.size) { intArrayOf(0, 0) })
}

fun solution(k: Int, dungeons: Array<IntArray>): Int {
    initCases(dungeons)

    var maxCount = 0
    for (case in cases) {
        var newK = k
        var count = 0
        for (dungeon in case) {
            if (newK < dungeon[0]) {
                continue
            }
            newK -= dungeon[1]
            count++
        }
        maxCount = max(maxCount, count)
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
