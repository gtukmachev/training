package tga.lru

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class LRUCacheTests {


    @Test
    fun test0() {
        val cache = LRUCache(0)

        cache[1] = 1
        assertThat( cache[1] ).isEqualTo(-1)

        cache[2] = 2
        assertThat( cache[2] ).isEqualTo(-1)
    }

    @Test
    fun test1() {
        val cache = LRUCache(1)

        cache[1] = 1
        assertThat( cache[1] ).isEqualTo(1)
        assertThat( cache[1] ).isEqualTo(1)

        cache[2] = 2
        assertThat( cache[1] ).isEqualTo(-1)
        assertThat( cache[2] ).isEqualTo(2)
        assertThat( cache[2] ).isEqualTo(2)
        assertThat( cache[1] ).isEqualTo(-1)
    }

    @Test
    fun test2() {
        val cache = LRUCache(2)

        cache[1] = 1
        cache[2] = 2

        assertThat( cache[1] ).isEqualTo(1)

        cache[3] = 3

        assertThat( cache[2] ).isEqualTo(-1)

        cache[4] = 4

        assertThat( cache[1] ).isEqualTo(-1)
        assertThat( cache[3] ).isEqualTo(3)
        assertThat( cache[4] ).isEqualTo(4)

    }

}

