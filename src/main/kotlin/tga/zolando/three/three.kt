package tga.zolando.three

import java.util.*

// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

fun main() {
    test( intArrayOf(5,2,4,6,3,7), 5)
    test( intArrayOf(5,2,4,6,3,1), 5)
    test( intArrayOf(1,1,1,1,1,1,1), 2)


}



fun test(A: IntArray, r: Int) {
    val task = "[${A.joinToString()}] -> $r"
    val actualR = solution(A)

    if (actualR == r) {
        println("ok : $task")
        return
    }
    println("FAIL : $task :: actual = $actualR")
}


// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

fun solution(A: IntArray): Int {

    val minHeap = PriorityQueue<Pair<Int, Int>>{ p1, p2 -> p1.first - p2.first }

    for (i in 1 .. (A.size-2) ) {
        minHeap.add(A[i] to i)
        // need to optimize (we need nly the first 7 elements) - it can be O(1) for memory compelexety
    }

    val (firstValue, firstPosition)  = minHeap.remove()
    val (secondValue, secondPosition)  = minHeap.remove()

    if (Math.abs(firstPosition - secondPosition) > 1) return firstValue + secondValue

    val (thirdValue, thirdPosition)  = minHeap.remove()
    if (Math.abs(firstPosition - thirdPosition) > 1) return firstValue + thirdPosition

    return secondValue + thirdValue;
}
