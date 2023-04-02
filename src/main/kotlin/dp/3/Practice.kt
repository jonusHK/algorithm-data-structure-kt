package dp.`3`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Integer.min


fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))
    val bw = BufferedWriter(OutputStreamWriter(System.out))

    repeat(br.readLine().toInt()) {
        val n = br.readLine().toInt()
        val files = br.readLine().split(" ").map { it.toInt() }

        val sums = Array(n + 1) { if (it == 0) 0 else files[it-1] }
        for (i in 1 until sums.size) {
            sums[i] += sums[i-1]
        }

        val dp = Array(n + 1) { Array(n + 1) { 0 } }

        for (i in 2 until n + 1) {
            for (j in 1 until n - i + 2) {
                var minValue = Int.MAX_VALUE
                for (k in 0 until i - 1) {
                    minValue = min(minValue, (dp[j][j+k] + dp[j+k+1][j+i-1]) + sums[j+i-1] - sums[j-1])
                }
                dp[j][j+i-1] = minValue
            }
        }

        bw.write(dp[1][n].toString() + "\n")
    }

    bw.flush()
    bw.close()
}
