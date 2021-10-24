package tga.K_Closest_Points_to_Origin

import org.assertj.core.api.Assertions
import org.junit.Test

class Tests {
    val s = Solution()

    @Test fun t1() = tst(intArrayOf( 1,3, -2,2), 1, intArrayOf(-2,2))

    @Test fun t2() = tst(intArrayOf( 3,3, 5,-1, -2,4), 2, intArrayOf(-2,4, 3,3))
//    sqrt(3 * 3   +  3* 3) = sqrt(9+9)  = sqrt(18)
//    sqrt(5 * 5   + -1*-1) = sqrt(25+1) = sqrt(26)
//    sqrt(-2*-2   +  4* 4) = sqrt(4+16) = sqrt(20)

    private fun tst(coordinates: IntArray, k: Int, expectedOutput: IntArray) {
        val points = Array(coordinates.size / 2){ intArrayOf(coordinates[it*2], coordinates[it*2+1]) }
        val expectedPoints = Array(expectedOutput.size / 2){ intArrayOf(expectedOutput[it*2], expectedOutput[it*2+1]) }


        val output = s.kClosest(points, k)
        Assertions.assertThat(output).isEqualTo(expectedPoints)
    }
}