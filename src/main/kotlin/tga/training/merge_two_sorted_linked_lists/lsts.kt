package tga.training.merge_two_sorted_linked_lists

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test


data class Node(val v: Int, var next: Node? = null){

    fun print() {
        print(v)
        next
            ?.also { print(", "); it.print() }
            ?: print("\n")
    }

    companion object {

        fun fromArray(arr: Array<Int>): Node? {
            val head = Node(0)
            var n = head
            arr.forEach { value ->
                n.next = Node(value)
                n = n.next!!
            }
            return head.next
        }

    }

}

fun Node?.joinToString(prefix: String = "[", separator: String = ", ", suffix: String = "]"): String {
    val s = StringBuilder(prefix)

    var el: Node? = this;
    while(el != null) {
        s.append(el.v)
        if (el.next != null) s.append(separator)
        el = el.next
    }

    s.append(suffix)
    return s.toString()
}


infix fun Node?.mergeSorted(list2: Node?): Node? {
    if (this == null) return list2
    if (list2 == null) return this

    var current: Node?
    var another: Node?
    if (this.v <= list2.v) { current = this;  another = list2 }
                      else { current = list2; another = this  }

    val head = current

    while (current != null && another != null) {
        val next = current.next
        if (next == null ||  next.v > another.v) {
            current.next = another
            another = next
        }
        current = current.next
    }

    return head
}


fun Node?.toArray(): Array<Int> {
    fun addLast(n: Node?, i: Int): Array<Int> {
        if (n == null) return Array(i){0}
        val arr = addLast(n.next, i+1)
        arr[i] = n.v
        return arr
    }
    return addLast(this, 0)
}

fun doTest(arr1: Array<Int>, arr2: Array<Int>, expected: Array<Int>) {

    val l1 = Node.fromArray(arr1)//; println("l1 = ${l1.joinToString()}")
    val l2 = Node.fromArray(arr2)//; println("l2 = ${l2.joinToString()}")

    val merged = l1 mergeSorted l2//; println("merged = ${merged.joinToString()}")

    val actualArr: Array<Int> = merged.toArray()

    assertThat(actualArr).isEqualTo(expected)

}

class tests {
    @Test fun simple_test() = doTest(arrayOf(1,3), arrayOf(2,4), arrayOf(1,2,3,4))

    @Test fun l1_is_empty_test() = doTest(arrayOf(), arrayOf(2,4), arrayOf(2,4))
    @Test fun l2_is_empty_test() = doTest(arrayOf(2,4), arrayOf(), arrayOf(2,4))
    @Test fun lists_are_empty_test() = doTest(arrayOf(), arrayOf(), arrayOf())

    @Test fun l1_is_shorter_test() = doTest(arrayOf(1,2,5,6,9), arrayOf(3,4,7,8,10,11,12), arrayOf(1,2,3,4,5,6,7,8,9,10,11,12))
    @Test fun l2_is_shorter_test() = doTest(arrayOf(1,2,5,7,8), arrayOf(3,4,6), arrayOf(1,2,3,4,5,6,7,8))

    @Test fun l1_is_the_first_one() = doTest(arrayOf(1,2), arrayOf(3), arrayOf(1,2,3))
    @Test fun l2_is_the_first_one() = doTest(arrayOf(2), arrayOf(1,3), arrayOf(1,2,3))
}

