package com.dhkpo.algorithm.tree

import kotlin.math.max
import kotlin.math.min


data class Node(
    var left: Int,
    var right: Int,
    var parent: Int,
    var value: Int
)

val tree = HashMap<Int, Node>()
var root: Int = -1
val levelMin = HashMap<Int, Int>()
val levelMax = HashMap<Int, Int>()
var levelDepth = 1
var nodeIdx = 1

fun inOrder(num: Int, level: Int): Unit {
    levelDepth = max(level, levelDepth)

    if (tree[num]!!.left != -1) {
        inOrder(tree[num]!!.left, level + 1)
    }
    levelMin[level] = min(levelMin[level]!!, nodeIdx)
    levelMax[level] = max(levelMax[level]!!, nodeIdx)
    nodeIdx++
    if (tree[num]!!.right != -1) {
        inOrder(tree[num]!!.right, level + 1)
    }
}

fun main(args: Array<String>) {
    val n = readln().toInt()

    repeat(n) {
        tree[it + 1] = Node(left = -1, right = -1, parent = -1, value = it + 1)
        levelMin[it + 1] = n
        levelMax[it + 1] = 1
    }

    repeat(n) {
        val (num, left, right) = readln().split(" ").map { it.toInt() }
        tree[num]!!.left = left
        tree[num]!!.right = right
        if (left != -1) tree[left]!!.parent = num
        if (right != -1) tree[right]!!.parent = num
    }

    for (i in 1..n) if (tree[i]!!.parent == -1) root = i

    inOrder(root, 1)

    var resultLevel = 1
    var resultWidth = 1
    for (i in 2..levelDepth) {
        val width: Int = levelMax[i]!! - levelMin[i]!! + 1
        if (width > resultWidth) {
            resultLevel = i
            resultWidth = width
        }
    }
    println("$resultLevel $resultWidth")
}