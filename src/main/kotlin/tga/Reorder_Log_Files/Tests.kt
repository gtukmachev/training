package tga.Reorder_Log_Files

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class Tests {

    val s = Solution()

    @Test fun t1() = tst(
        arrayOf("l evmbcyaqe zx","pk amxcdvhuhavi","cx 965 84 9 20","b 401957007189","ez xodjwnc awg","96t oxgsdkuj j","af 611441988 6","l9d 21 6 77 795","l khuxbzszqarfz","4zj 6115548620","l6 fzqtxlo qi j","anr 76976970 17","of vtqfbyxaxtce","j 544232 60 554","108 u amvyjml s"),
        arrayOf("pk amxcdvhuhavi","l evmbcyaqe zx","l6 fzqtxlo qi j","l khuxbzszqarfz","96t oxgsdkuj j","108 u amvyjml s","of vtqfbyxaxtce","ez xodjwnc awg","cx 965 84 9 20","b 401957007189","af 611441988 6", "l9d 21 6 77 795", "4zj 6115548620", "anr 76976970 17", "j 544232 60 554" )
    )

    @Test fun t2() = tst(
        arrayOf("a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act zoo"),
        arrayOf("g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4 7")
    )

    @Test fun te() = tst(
        arrayOf("j mo", "5 m w", "g 07", "o 2 0", "t q h"),
        arrayOf("5 m w","j mo","t q h","g 07","o 2 0")
    )


    private fun tst(input: Array<String>, expectedOutput: Array<String>) {
        val output = s.reorderLogFiles(input)
        assertThat(output).isEqualTo(expectedOutput)
    }

}