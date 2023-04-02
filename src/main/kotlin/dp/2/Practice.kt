package dp.`2`

import java.lang.Integer.min

val mapping = hashMapOf(
    'A' to 3, 'B' to 2, 'C' to 1, 'D' to 2, 'E' to 4, 'F' to 3, 'G' to 1,
    'H' to 3, 'I' to 1, 'J' to 1, 'K' to 3, 'L' to 1, 'M' to 3, 'N' to 2,
    'O' to 1, 'P' to 2, 'Q' to 2, 'R' to 2, 'S' to 1, 'T' to 2, 'U' to 1,
    'V' to 1, 'W' to 1, 'X' to 2, 'Y' to 2, 'Z' to 1
)

fun main(args: Array<String>) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val (a, b) = readln().split(" ")

    val minLength = min(n, m)

    var newStr = ""
    repeat(minLength) {
        newStr += a[it].toString() + b[it].toString()
    }

    newStr += if (minLength == n) {
        b.substring(n)
    } else {
        a.substring(m)
    }

    val newIntArr = IntArray(newStr.length)
    repeat(newStr.length) {
        newIntArr[it] = mapping[newStr[it]]!!
    }

    var cnt = 0
    while (cnt < newIntArr.size - 2) {
        for (i in (0 until newIntArr.size - cnt - 1)) {
            newIntArr[i] = (newIntArr[i] + newIntArr[i+1]) % 10
        }
        cnt++
    }

    val resultStr = newIntArr.slice(0..1).joinToString("").toInt().toString()
    println("$resultStr%")
}