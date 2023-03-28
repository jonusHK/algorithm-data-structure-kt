package heap

import java.util.PriorityQueue
import kotlin.math.pow


/* 백준 - 소수의 곱 */

fun main(args: Array<String>) {
    val queue = PriorityQueue<Long>()

    var (k, n) = readln().split(" ").map { it.toInt() }
    val nums: Array<Long> = readln().split(" ").map { it.toLong() }.toTypedArray()

    queue.addAll(nums)

    var pop = 0L
    while (n-- > 0) {
        pop = queue.poll()

        for (v in nums) {
            val temp: Long = pop * v
            if (temp >= 2.0.pow(31).toLong()) {
                break
            }
            queue.add(temp)
            // 중복된 값이 추가되지 않도록 순서대로 곱해진 수만 허용
            if (pop % v == 0L) {
                break
            }
        }
    }
    println(pop)
}
