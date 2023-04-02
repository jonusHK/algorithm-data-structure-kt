package backtracking.`1`

import java.lang.Integer.max


const val maxCnt = 5
val degreeArr = arrayOf(0, 90, 180, 270)
var maxValue: Int = 0


fun rotateArray(arr: Array<Array<Int>>, degree: Int): Array<Array<Int>> {
    val n = arr.size
    val m = arr[0].size

    val rotatedArray = Array(n) { Array(m) { 0 } }

    repeat(arr.size) { i ->
        repeat(arr[0].size) { j ->
            when (degree) {
                degreeArr[0] -> rotatedArray[i][j] = arr[i][j]
                degreeArr[1] -> rotatedArray[i][j] = arr[n-1-j][i]
                degreeArr[2] -> rotatedArray[i][j] = arr[n-1-i][m-1-j]
                degreeArr[3] -> rotatedArray[i][j] = arr[j][m-1-i]
            }
        }
    }
    return rotatedArray
}


fun findSameValueAndUnion(arr: Array<Int>, startIdx: Int) {
    var idx = startIdx + 1
    while (idx < arr.size) {
        if (arr[idx] == 0) {
            idx++
            continue
        }
        if (arr[startIdx] == arr[idx]) {
            arr[startIdx] *= 2
            arr[idx] = 0
        }
        break
    }
}


fun unionArrayToLeft(arr: Array<Array<Int>>) {
    for (innerArr in arr) {
        for (i in 0 until innerArr.size - 1) {
            if (innerArr[i] == 0) {
                continue
            } else {
                findSameValueAndUnion(innerArr, i)
            }
        }
        repeat(innerArr.size) {
            for (i in 0 until innerArr.size - 1) {
                if (innerArr[i] == 0 && innerArr[i+1] > 0) {
                    innerArr[i] = innerArr[i+1]
                    innerArr[i+1] = 0
                }
            }
        }
    }
}

fun getMaxValue(arr: Array<Array<Int>>) {
    for (innerArr in arr) {
        maxValue = max(maxValue, innerArr.max())
    }
}

fun backtracking(arr: Array<Array<Int>>, degree: Int, count: Int) {
    val rotatedArray: Array<Array<Int>> = rotateArray(arr, degree)
    unionArrayToLeft(rotatedArray)
    getMaxValue(rotatedArray)

    if (count == maxCnt) {
        return
    }

    for (d in degreeArr) {
        backtracking(rotatedArray, d, count + 1)
    }
}


fun main(args: Array<String>) {
    val n = readln().toInt()
    val arr = Array(n) { Array(n) { 0 } }

    repeat(n) { i ->
        for ((j, v) in readln().split(" ").map { it.toInt() }.withIndex()) {
            arr[i][j] = v
        }
    }

    for (d in degreeArr) {
        backtracking(arr, d, 1)
    }

    println(maxValue)
}
