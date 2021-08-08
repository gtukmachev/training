package tga.training.heap

interface Heap<T : Comparable<T>> : Iterable<T> {

    fun add(element: T)
    fun remove(): T

    val head: T
    val size: Int

}