package tga.Longest_Palindromic_Ssubstring

import org.assertj.core.api.Assertions
import org.junit.Test

class Tests {
    val s = Solution()


    @Test fun t1() = tst("cbbd", "bb")
    @Test fun t2() = tst("", "")
    @Test fun t3() = tst("1", "1")
    @Test fun t4() = tst("11", "11")
    @Test fun t51() = tst("a11", "11")
    @Test fun t52() = tst("a111", "111")
    @Test fun t54() = tst("a121", "121")
    @Test fun t61() = tst("11a", "11")
    @Test fun t62() = tst("111a", "111")

    @Test fun ts1() = tst("qwertytrewq", "qwertytrewq")
    @Test fun ts21() = tst("2qwertytrewqqwertytrewq11", "qwertytrewqqwertytrewq")
    @Test fun ts22() = tst("qwertytrewqqwertytrewq11", "qwertytrewqqwertytrewq")
    @Test fun ts23() = tst("qwertytrewqaqwertytrewq11", "qwertytrewqaqwertytrewq")
    @Test fun ts24() = tst("11qwertytrewqaqwertytrewq", "qwertytrewqaqwertytrewq")
    @Test fun ts25() = tst("1qwertytrewqaqwertytrewq", "qwertytrewqaqwertytrewq")
    @Test fun ts26() = tst("qwertytrewqaqwertytrewq", "qwertytrewqaqwertytrewq")

    private fun tst(input: String, expectedOutput: String) {
        val output = s.longestPalindrome(input)
        Assertions.assertThat(output).isEqualTo(expectedOutput)
    }
}