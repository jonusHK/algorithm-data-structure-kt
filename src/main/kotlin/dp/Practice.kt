package dp

import java.lang.Integer.max


/* 백준 - 가장높은탑쌓기 */

data class MultiComponent(
    val index: Int,
    val area: Int,
    val height: Int,
    val weight: Int
)

fun main(args: Array<String>) {
    val n = readln().toInt()

    val components = mutableListOf<MultiComponent>()

    repeat(n) { idx ->
        val (a, h, w) = readln().split(" ").map { it.toInt() }
        components.add(
            MultiComponent(
                index = idx + 1,
                area = a,
                height = h,
                weight = w
            )
        )
    }

    components.sortBy { it.weight }

    val dp = Array(n) { 0 }
    for (i in components.indices) {
        dp[i] = components[i].height
    }

    for (i in 1 until components.size) {
        for (j in 0 until i) {
            if (components[j].area < components[i].area) {
                dp[i] = max(dp[i], dp[j] + components[i].height)
            }
        }
    }

    var maxHeight: Int = dp.max()
    var idx = n - 1
    val result = mutableListOf<Int>()

    while (idx >= 0) {
        if (maxHeight == dp[idx]) {
            result.add(0, components[idx].index)
            maxHeight -= components[idx].height
        }
        idx--
    }

    println(result.size)
    result.forEach {
        println(it)
    }
}
