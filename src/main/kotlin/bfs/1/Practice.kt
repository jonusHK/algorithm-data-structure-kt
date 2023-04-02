package bfs.`1`

import java.lang.Integer.max


val directionX = arrayOf(-1, 0, 1, 0)
val directionY = arrayOf(0, 1, 0, -1)
var maxCnt = 0
var r = 0
var c = 0

fun bfs(board: Array<String>, start: Pair<Int, Int>) {
    val queue = LinkedHashSet<Triple<Int, Int, String>>()
    queue.add(
        Triple(start.first, start.second, board[start.first][start.second].toString())
    )

    while (queue.isNotEmpty()) {
        val (x, y, str) = queue.first().also { queue.remove(it) }

        maxCnt = max(maxCnt, str.length)

        repeat(4) {
            val dx = x + directionX[it]
            val dy = y + directionY[it]

            if (dx in 0 until r && dy in 0 until c && board[dx][dy] !in str) {
                queue.add(
                    Triple(dx, dy, str + board[dx][dy])
                )
            }
        }
    }
}

fun main(args: Array<String>) {
    val (row, column) = readln().split(" ").map{ it.toInt() }
    r = row
    c = column

    val board = Array(r) { "" }

    repeat(r) {
        board[it] = readln()
    }

    bfs(board, 0 to 0)

    println(maxCnt)
}
