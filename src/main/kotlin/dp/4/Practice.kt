/* 프로그래머스 - N으로 표현 */

package dp.`4`

fun calc(a: HashSet<Int>, b: HashSet<Int>) = sequence {
    a.forEach { x ->
        b.forEach { y ->
            yield(x + y)
            yield(x - y)
            yield(x * y)
            if (x != 0 && y != 0) yield(x / y)
        }
    }
}

fun solution(N: Int, number: Int): Int {
    if (number == N) {
        return 1
    }

    val size = 8
    val dp = Array(size + 1) { hashSetOf<Int>() }
    dp[1].add(N)

    for (i in 2..size) {
        dp[i].add(Array(i) { N }.joinToString("").toInt())
        for (j in (1 until i)) {
            dp[i].addAll(calc(dp[j], dp[i - j]))
        }
        if (number in dp[i]) {
            return i
        }
    }
    return -1
}

fun main(args: Array<String>) {
    val n = 4
    val number = 31

    println(solution(n, number))
}