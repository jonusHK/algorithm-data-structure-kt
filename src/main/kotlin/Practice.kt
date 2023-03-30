import java.lang.Integer.max

val directionX = arrayOf(-1, 0, 1, 0)
val directionY = arrayOf(0, 1, 0, -1)
var maxCnt: Int = 0

fun getPositions(board: Array<String>, position: Pair<Int, Int>): MutableList<Pair<Int, Int>> {
    val positions = mutableListOf<Pair<Int, Int>>()
    repeat(4) {
        val dx = position.first + directionX[it]
        val dy = position.second + directionY[it]

        if (dx >= 0 && dx < board.size && dy >= 0 && dy < board[0].length) {
            positions.add(dx to dy)
        }
    }
    return positions
}

fun dfs(board: Array<String>, position: Pair<Int, Int>, set: HashSet<Char>, count: Int) {
    if (board[position.first][position.second] in set) {
        return
    }
    val copiedSet = set.toHashSet()
    copiedSet.add(board[position.first][position.second])
    maxCnt = max(maxCnt, count)

    val nextPositions = getPositions(board, position)
    if (nextPositions.isEmpty()) {
        return
    }
    for (pos in nextPositions) {
        dfs(board, pos, copiedSet, count + 1)
    }
}

fun main(args: Array<String>) {
    val (r, c) = readln().split(" ").map { it.toInt() }
    val board = Array(r) { "" }

    repeat(r) {
        board[it] = readln()
    }
    dfs(board, 0 to 0, HashSet(), 1)

    println(maxCnt)
}
