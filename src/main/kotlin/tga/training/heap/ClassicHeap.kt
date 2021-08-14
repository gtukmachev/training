package tga.training.heap

open class ClassicHeap<T : Comparable<T>>(
    private val comparator: Comparator<T> = NaturalComparator()
) : Heap<T>
{

    protected val arr: ArrayList<T> = ArrayList()

    override val size: Int get() = arr.size

    override val head: T get() = arr[0]

    override fun iterator(): Iterator<T> = arr.iterator()

    override fun add(element: T) {
        arr.add(element)
        var i = arr.size - 1

        while (i > 0) {
            val parent = ((i+1) / 2) - 1
            val parentValue = arr[parent]
            if (comparator.compare(element, parentValue) >= 0) return

            arr[parent] = element
            arr[i] = parentValue

            i = parent
        }
    }

    override fun remove(): T {
        val head = arr[0]

        val element = arr.removeAt(arr.size-1)
        if  (arr.size == 0) return head

        arr[0] = element

        var i = 0

        while (true) {
            val leftIndex = i * 2 + 1
            if (leftIndex >= arr.size) break
            val leftValue = arr[leftIndex]

            val rightIndex = leftIndex + 1
            val rightValue = if (rightIndex < arr.size) arr[rightIndex] else element

            if (comparator.compare(element, leftValue) <= 0 && comparator.compare(element, rightValue) <= 0) break

            if (comparator.compare(element, rightValue) > 0 && comparator.compare(rightValue, leftValue) < 0) {
                arr[rightIndex] = element
                arr[i] = rightValue
                i = rightIndex
            } else {
                arr[leftIndex] = element
                arr[i] = leftValue
                i = leftIndex
            }
        }

        return head
    }

    protected class NaturalComparator<T: Comparable<T>> : Comparator<T> {
        override fun compare(o1: T, o2: T): Int = o1.compareTo(o2)
    }

}