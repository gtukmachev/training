package tga.training.queue.removals


import org.assertj.core.api.Assertions.assertThat
import org.junit.Test



fun findPositions(arr: Array<Int>, x: Int): Array<Int> {

    val positions: Array<Int> = Array(x){-1}

    var head = 0
    var back = head

    fun incLoop(i: Int) = when (val j = i+1) {
            arr.size -> 0
                else -> j
    }

    fun log(iteration: Int) {
        fun arrToStr(a: Array<Int>, index: Int): String {
            return a.mapIndexed{ i, el  ->
                    val s = if (i == index) "_" else " "
                    "$s$el$s".padStart(5)
            }.joinToString()

        }

        println("$iteration :: h:$head  q:[${arrToStr(arr, head)}] -> p:[${arrToStr(positions, iteration)}]")
    }

    //log(0)

    for (iteration in 0 until x) {

        // step 1 - pop x elements
        var maxElement = -1
        var maxElementIndex = -1

        var poppedElements = 0
        do {
            val index = head
            val element = arr[head]; head = incLoop(head)

            if (element >= 0) { // the element is not removed out from the queue yet
                poppedElements++

                if (element > maxElement) {
                    maxElement = element
                    if (maxElementIndex > 0) arr[maxElementIndex] --
                    maxElementIndex = index
                } else {
                    if (element > 0) arr[index] -= 1
                }
            }

        } while ( poppedElements < x && head != back)

        if (maxElementIndex == -1) {
            // !!! - the income queue is too small - !!!
            break
        }
        positions[iteration] = maxElementIndex+1

        arr[maxElementIndex] = -1 // mark as removed

        back = head
        //log(iteration+1)

    }

    return positions
}


class QueueRemovalsTests {

    @Test fun test1() = findPositionsGenericTest(arrayOf(1,2,2,3,4,5), 5, arrayOf(5,6,4,1,2))

    private fun findPositionsGenericTest(arr: Array<Int>, x: Int, requiredResult: Array<Int>) {
        val initialArr = arr.copyOf()
        assertThat(findPositions(arr, x))
            .describedAs("findPositions([${initialArr.joinToString()}], $x) ")
            .isEqualTo(requiredResult)
    }

}