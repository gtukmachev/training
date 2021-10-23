package tga.Trapping_Rain_Water

import org.assertj.core.api.Assertions
import org.junit.Test

class Tests {

    val s = Solution()

    @Test fun t1() = tst(intArrayOf(4,2,3), 1)

    private fun tst(input: IntArray, expectedOutput: Int) {
        val output = s.trap(input)
        Assertions.assertThat(output).isEqualTo(expectedOutput)
    }
}