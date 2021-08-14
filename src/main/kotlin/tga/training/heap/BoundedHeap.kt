package tga.training.heap

class BoundedHeap<T : Comparable<T>>(
    val maxSize: Int,
    private val comparator: Comparator<T> = NaturalComparator()
): ClassicHeap<T>(comparator) {

    override fun add(element: T) {
        super.add(element)
        while (this.size > maxSize) arr.removeLast()
    }

}