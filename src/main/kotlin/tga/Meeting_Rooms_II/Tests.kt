package tga.Meeting_Rooms_II

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Tests {

    val s = Solution()

    @Test fun t1() = test(1, 2,3, 4,5, 6,7 )
    @Test fun t2() = test(2, 2,3, 4,5, 6,7,  2,7 )


    fun test(expectedResult: Int, vararg rawIntervals: Int) {
        val intervals = Array(rawIntervals.size/2){ IntArray(2) }
        for(i in 0 until rawIntervals.size/2) {
            intervals[i][0] = rawIntervals[i*2]
            intervals[i][1] = rawIntervals[i*2+1]
        }

        val result = s.minMeetingRooms(intervals)
        assertThat(result).isEqualTo(expectedResult)
    }

}