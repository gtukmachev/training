package tga.K_Closest_Points_to_Origin

import java.util.*

class Solution {
    inline fun IntArray.toLen():Int = this[0]*this[0] + this[1]*this[1]

    fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        if (points.size <= k) return points

        val len = IntArray(points.size)
        val maxHeap = PriorityQueue<Int>(){i1, i2 -> len[i2] - len[i1]}

        for(i in 0 until k) {
            len[i] = points[i].toLen()
            maxHeap.add(i)
        }

        for(i in k until points.size){
            len[i] = points[i].toLen()
            val topHeapPointIndex: Int = maxHeap.peek()
            if (len[i] < len[topHeapPointIndex]) {
                maxHeap.remove()
                maxHeap.add(i)
            }
        }

        val result = Array(k){points[ maxHeap.remove() ]}

        return result
    }
}