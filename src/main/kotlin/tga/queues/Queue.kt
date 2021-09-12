package tga.queues

// We don’t provide test cases in this language yet, but have outlined the signature for you. Please write your code below, and don’t forget to test edge cases!

fun main(args : Array<String>) {
    // Call findPositions() with test cases here

    testQueue()
}


fun findPositions(arr: Array<Int>, x: Int): Array<Int> {
    val queue = Queue(arr)

    //step 1 : pop x elements
    var i = 0; while (queue.size > 0 && i < x) {
        i++

        val el = queue.pop()

    }


    // Write your code here
    return emptyArray()
}


class Queue(
    val arr: Array<Int>
) {

    private var head = 0
    private val lastIndex = arr.size - 1
    private var back = lastIndex

    var length_: Int = arr.size

    val size: Int get() = length_

    fun push(element: Int) {
        if (length_ >= arr.size) throw RuntimeException("The queue size exceeded")
        length_++
        back = loopStepForward(back)
        arr[back] = element
    }

    fun pop(): Int {
        if (length_ == 0) throw RuntimeException("The Queue is empty")
        length_ --
        val element = arr[head]
        head = loopStepForward(head)
        return element
    }

    private fun loopStepForward(index: Int) = when (index) {
        lastIndex -> 0
        else -> index + 1
    }

}


fun testQueue() {

    val queue = Queue(arrayOf(1,2,3))

    assertTrue(queue.pop() == 1, "queue.pop() == 1")
    assertTrue(queue.pop() == 2, "queue.pop() == 2")
    assertTrue(queue.pop() == 3, "queue.pop() == 3")

    assertFailWithMessage("emptyQueue.pop()", "The Queue is empty"){ queue.pop() }

    queue.push(5); assertTrue(queue.arr contentEquals arrayOf(5,2,3), "[${queue.arr.joinToString()}] == arrayOf(5,2,3)")
    queue.push(6); assertTrue(queue.arr contentEquals arrayOf(5,6,3), "[${queue.arr.joinToString()}] == arrayOf(5,6,2)")
    queue.push(7); assertTrue(queue.arr contentEquals arrayOf(5,6,7), "[${queue.arr.joinToString()}] == arrayOf(5,6,7)")

    assertTrue(queue.pop() == 5, "queue.pop() == 5")
    queue.push(8); assertTrue(queue.arr contentEquals arrayOf(8,6,7), "[${queue.arr.joinToString()}] == arrayOf(5,6,7)")
    assertTrue(queue.pop() == 6, "queue.pop() == 6")
    queue.push(9); assertTrue(queue.arr contentEquals arrayOf(8,9,7), "[${queue.arr.joinToString()}] == arrayOf(5,6,7)")

    assertFailWithMessage("fullQueue.push(10)", "The queue size exceeded"){ queue.push(10) }

}

fun assertFailWithMessage(checkName: String, msg: String, action: () -> Unit) {
    try{
        action()
        throw RuntimeException("FAIL: $checkName")
    } catch (e: Exception) {
        assertTrue(e.message == msg, "$checkName : '${e.message}' == '$msg'")
    }

}

fun assertTrue(value: Boolean, msg: String = "") {
    if (value) {
        println("OK: $msg")
        return
    }
    println("FAIL: $msg")
}