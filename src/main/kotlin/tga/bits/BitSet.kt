// We don’t provide test cases in this language yet, but have outlined the signature for you. Please write your code below, and don’t forget to test edge cases!

import java.util.*

fun main(args : Array<String>) {
    testToInt(intArrayOf(3,1,2), 312)
    testToInt(intArrayOf(1,2,3,4,5,6,7,8), 12345678)

    val arr = intArrayOf(2,8,4,3,6,7,1,5)
    testToIntSorted(arr, 12345678)
    testToInt(arr, 28436715)



    testMinOperations(intArrayOf(3,1,2), 2)
}

fun testMinOperations(arr: IntArray, result: Int) = genericTest("minOperations", arr, result){arr -> minOperations(arr) }
fun testToInt        (arr: IntArray, result: Int) = genericTest("toInt",         arr, result){arr -> arr.toInt()        }
fun testToIntSorted  (arr: IntArray, result: Int) = genericTest("toIntSorted",   arr, result){arr -> arr.toIntSorted()  }

fun genericTest(name: String, arr: IntArray, result: Int, action: (IntArray) -> Int) {
    val task = "$name : [${arr.joinToString()}] -> $result"

    val actualResult = action(arr)

    if (actualResult != result) {
        println("FAIL : $task :: actual result = $actualResult")
        return
    }

    println("ok : $task")
}


fun IntArray.toInt(): Int {
    var result = 0
    for (i in 0 until this.size) {
        result = result*10 + this[i]
    }
    return result
}

fun IntArray.toIntSorted(): Int {
    val sortedArr: IntArray = this.copyOf()
    sortedArr.sort()
    return sortedArr.toInt()
}

fun minOperations(arr: IntArray): Int {
    val sorted = arr.toIntSorted()
    val N = arr.size

    if (arr.toInt() == sorted) return 0

    val visited = BitSet(1*2*3*4*5*6*7*8) // 8! = 40320

    // if sorted -> true

    val queue: Queue<Pair<Int, IntArray>> = LinkedList() //!! optimize memory (deep + node)
    queue.add(0 to arr)

    while (queue.isNotEmpty()) {
        val (deep, permutation) = queue.remove()
        val deepPlusOne = deep+1

        // generate all possible permutation of the next level
        for (len in 2..N) {
            for (startPosition in 0..(N-len)) {
                val nextPermutation = inverse(permutation, startPosition, len)
                val intNextPermutation = nextPermutation.toInt()
                if ( intNextPermutation == sorted ) return deepPlusOne
                if ( visited.put(intNextPermutation) ) queue.add(deepPlusOne to nextPermutation)
            }
        }
    }

    throw RuntimeException("Sorted permutation is not achieved by the algorithm :-(")
}


fun inverse(permutation: IntArray, startPosition: Int, len: Int): IntArray {
    var first = startPosition
    var last = first + len - 1

    val inversed: IntArray = permutation.copyOf()

    while (first < last) {
        inversed[first] = permutation[last ]
        inversed[last ] = permutation[first]
        first++; last--
    }

    return inversed
}

class BitSet(capacity: Int) {

    private val mem = IntArray(capacity / 32 + 1) // an Int allocate exact 32 bits

    fun put(element: Int): Boolean { // element should be  > 0 !!!!
        val offset = element shr 5         // = element / 32
        val bit = (element shl 27) ushr 27 // = element - offset
        val mask = 1 shl bit

        // println("offset = $offset | bit = $bit | mask = ${mask.toBinaryString()}")

        if ( (mem[offset] and mask) != 0) return false

        mem[offset] = mem[offset] or mask
        return true
    }

    /*override fun toString(): String {
        val sb = StringBuffer("BitSet{(${mem.size})")
        for (i in (mem.size-1) downTo 0 ) {
            sb.append(mem[i].toBinaryString()).append(" ")
        }
        sb.append("}")
        return sb.toString()
    }*/
}


/*
2 - n-1
3 - n-2
.
n - 1

(1 + n-1) * (n-1)      n^2 - n
-----------------   = ---------
       2                  2   

64-8 -> 56 -> 28

28 ^ 8 = 377,801,998,336  

0 <= solution < N
8!
4321 -> 40320 * (Int (4b)) ~ 157Kb -> 5Kb (for bit set)

3421 2341 1234
4231 4123
4312
*/