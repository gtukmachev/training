package tga.div

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

object intDivisor {
    private val HALF_OF_INT_MAX = Int.MAX_VALUE / 2

    fun divide(dividend: Int, divisor: Int): Int {
        return doDivide(dividend, divisor)
    }

    fun doDivide(dividend: Int, divisor: Int): Int {
        if (dividend == 0) return 0
        if (dividend == divisor) return 1
        if (divisor ==  Int.MIN_VALUE) return 0
        if (divisor == 1) return dividend
        if (divisor == -1 && dividend == Int.MIN_VALUE) return Int.MAX_VALUE
        if (divisor == -1) return -dividend

        val result = if (dividend == Int.MIN_VALUE) {
            val absDivisor = Math.abs(divisor)
            divideAbs(Math.abs(dividend + absDivisor), absDivisor) + 1
        } else {
            divideAbs(Math.abs(dividend), Math.abs(divisor))
        }


        if ( (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) return result
        return -result
    }

    fun divideAbs(dividend: Int, divisor: Int): Int {
        var result = 0;
        var t = 1
        var rest = dividend
        var d = divisor

        while (rest >= d) {
            rest -= d
            result += t
            if (d < HALF_OF_INT_MAX) {
                d += d
                t += t
            }
        }

        if (rest < divisor) return result

        return result + divideAbs(rest, divisor)
    }

}

class IntDivisorTests {

    @Test fun test_100_20() = t(100, 20)
    @Test fun test_100_21() = t(100, 21)
    @Test fun test_121_20() = t(121, 20)


    @Test fun test_max_1() = t(Int.MAX_VALUE, 1)
    @Test fun test_max_2() = t(Int.MAX_VALUE, 2)
    @Test fun test_max_m1() = t(Int.MAX_VALUE, -1, -Int.MAX_VALUE)
    @Test fun test_max_m2() = t(Int.MAX_VALUE, -2)

    @Test fun test_1_max_() = t( 1, Int.MAX_VALUE, 0)
    @Test fun test_2_max_() = t( 2, Int.MAX_VALUE, 0)
    @Test fun test_m1_max() = t(-1, Int.MAX_VALUE, 0)
    @Test fun test_m2_max() = t(-2, Int.MAX_VALUE, 0)

    @Test fun test_min_1()  = t(Int.MIN_VALUE, 1)
    @Test fun test_min_2()  = t(Int.MIN_VALUE, 2)
    @Test fun test_min_m1() = t(Int.MIN_VALUE, -1, Int.MAX_VALUE)
    @Test fun test_min_m2() = t(Int.MIN_VALUE, -2)

    @Test fun test_1_min()  = t( 1, Int.MIN_VALUE, 0)
    @Test fun test_2_min()  = t( 2, Int.MIN_VALUE, 0)
    @Test fun test_m1_min() = t(-1, Int.MIN_VALUE, 0)
    @Test fun test_m2_min() = t(-2, Int.MIN_VALUE, 0)

    @Test fun test_min_min() = t(Int.MIN_VALUE, Int.MIN_VALUE, 1)
    @Test fun test_max_max() = t(Int.MAX_VALUE, Int.MAX_VALUE, 1)
    @Test fun test_min_max() = t(Int.MIN_VALUE, Int.MAX_VALUE, -1)
    @Test fun test_max_min() = t(Int.MAX_VALUE, Int.MIN_VALUE, 0)

    @Test fun test_10_2() = t(10, 2)


    fun t(a: Int, b: Int, res: Int? = null){ assertThat(intDivisor.divide( a, b )).describedAs(" $a / $b ").isEqualTo( res ?: (a / b) ) }

}