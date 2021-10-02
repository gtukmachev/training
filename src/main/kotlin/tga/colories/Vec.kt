package tga.colories

import java.lang.Math.sqrt

data class Vec(var x: Double = 0.0, var y: Double = 0.0, var z: Double = 0.0) {

    operator fun plusAssign (v: Vec) { x += v.x; y += v.y; z += v.z; }
    operator fun minusAssign(v: Vec) { x -= v.x; y -= v.y; z -= v.z; }

    operator fun plus(v: Vec) = Vec(x + v.x, y + v.y, z + v.z)
    operator fun minus(v: Vec) = Vec(x - v.x, y - v.y, z - v.z)

    operator fun unaryPlus() {}
    operator fun unaryMinus() {x = -x; y = -y; z = -z}

    operator fun inc():Vec {x=x+1; y=y+1; z=z+1; return this}
    operator fun dec():Vec {x=x-1; y=y-1; z=z-1; return this}

    operator fun times(k: Double): Vec = Vec(x*k, y*k, z*k)

    operator fun get(i: Int): Double = when(i) {
        0 -> x
        1 -> y
        2 -> z
        else -> throw IndexOutOfBoundsException("A vec has only [0,1,2] indexes, but requested: $i")
    }


    fun procentize(): Vec {
        val sum = x + y + z
        x /= sum
        y /= sum
        z /= sum

        return this
    }


    fun toStr(p: Int, l: Int): String {
        return "Vec(${x.asDecimalStr(p).padStart(l)},${y.asDecimalStr(p).padStart(l)},${z.asDecimalStr(p).padStart(l)})"
    }

    override fun toString(): String = toStr(1,5)
}

data class Line(var p0: Vec, var v: Vec) {

    fun distanceToPoint(p: Vec): Double {
        val dx: Double = p.x - p0.x
        val dy: Double = p.y - p0.y
        val dz: Double = p.z - p0.z

        val l = v.x
        val m = v.y
        val n = v.z

        val lineVecLen2 = l*l + m*m + n*n

        val M =
                    (dy*n-dz*m)*(dy*n-dz*m)+
                    (dz*l-dx*n)*(dz*l-dx*n)+
                    (dx*m-dy*l)*(dx*m-dy*l)

        val k = M / lineVecLen2

        val d = sqrt(k)

        return d
    }
}


fun Array<Vec>.sum(): Vec {
    val v = Vec()
    this.forEach { v += it }
    return v
}