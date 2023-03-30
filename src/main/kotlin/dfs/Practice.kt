package dfs

import java.lang.Integer.max


val directionX = arrayOf(-1, 0, 1, 0)
val directionY = arrayOf(0, 1, 0, -1)
var maxCnt: Int = 0
var r: Int = 0
var c: Int = 0

fun dfs(board: Array<String>, position: Pair<Int, Int>, str: String, count: Int) {
    if (board[position.first][position.second] in str) {
        return
    }

    val addedStr = str + board[position.first][position.second]

    maxCnt = max(maxCnt, count)

    repeat(4) {
        val dx = position.first + directionX[it]
        val dy = position.second + directionY[it]

        if (dx in 0 until r && dy in 0 until c) {
            dfs(board, dx to dy, addedStr, count + 1)
        }
    }
}

fun main(args: Array<String>) {
    val (row, column) = readln().split(" ").map { it.toInt() }
    r = row
    c = column

    val board = Array(r) { "" }

    repeat(r) {
        board[it] = readln()
    }

    dfs(board, 0 to 0, "", 1)

    println(maxCnt)
}
