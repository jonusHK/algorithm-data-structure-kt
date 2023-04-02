package greedy.`1`


fun main(args: Array<String>) {
    val craneCnt = readln().toInt()
    val craneWeight = readln().split(" ").map { it.toInt() }.toMutableList()
    val boxCnt = readln().toInt()
    val boxWeight = readln().split(" ").map { it.toInt() }.toMutableList()

    craneWeight.sort()
    craneWeight.reverse()

    boxWeight.sort()
    boxWeight.reverse()

    if (boxWeight[0] > craneWeight[0]) {
        println(-1)
        return
    }

    var cnt = 0

    while (boxWeight.isNotEmpty()) {
        var boxIdx = 0
        var craneIdx = 0
        while (craneIdx < craneCnt) {
            if (boxIdx == boxWeight.size) break
            if (craneWeight[craneIdx] >= boxWeight[boxIdx]) {
                boxWeight.removeAt(boxIdx)
                craneIdx++
            } else {
                boxIdx++
            }
        }
        cnt++
    }
    println(cnt)
}
