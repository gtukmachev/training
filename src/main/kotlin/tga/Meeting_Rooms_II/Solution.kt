package tga.Meeting_Rooms_II

import java.util.*

class Solution {

    fun minMeetingRooms(intervals: Array<IntArray>): Int {
        val N = intervals.size
        if (N == 0) return 0
        if (N == 1) return 1

        val byStart  = PriorityQueue<IntArray>{ a, b -> if (a[0] == b[0]) a[1] - b[1] else a[0] - b[0] }
        val byFinish = PriorityQueue<IntArray>{ a, b -> a[1] - b[1] }

        for (interval in intervals) byStart.add(interval)

        byFinish.add( byStart.remove() )
        var maxCapacity = 1
        var capacity = 1

        repeat(N-1) {
            val newInterval = byStart.remove()
            val activeInterval = byFinish.peek()

            if (newInterval[0] <= activeInterval[1]) {
                // new.start <= active.finish   ::    [      (   ]      )
                capacity++; if (capacity > maxCapacity) maxCapacity = capacity
            } else {
                // new.start > active.finish   ::    [          ] (    )
                byFinish.remove()
            }
            byFinish.add(newInterval)
        }

        return maxCapacity
    }


}