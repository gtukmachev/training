package tga.colories

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VecTests {

    @Test fun distance1() {
        val p = Vec(-4.0, 3.0, 5.0)
        val line = Line( p0=Vec(1.0, -5.0, -1.0), v = Vec(-2.0,3.0,4.0) )

        assertThat( line.distanceToPoint(p) ).isEqualTo(3.0)
    }

    @Test fun distance2() {
        val p = Vec(0.0, 2.0, 3.0)
        val line = Line( p0=Vec(3.0, 1.0, -1.0), v = Vec(2.0,1.0,2.0) )
        assertThat( line.distanceToPoint(p) ).isEqualTo(5.0)
    }

}