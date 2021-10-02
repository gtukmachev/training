package tga.colories

import java.util.ArrayList
import kotlin.random.Random


fun <T> Array<T>.iterateIndexedRandomlyButOnceWhile( action: (i: Int, v: T ) -> Boolean ) {

    val list = ArrayList<Int>()
    (0 until this.size).forEach { list.add(it) }

    var lastResult = true

    while(lastResult && list.isNotEmpty()) {
        val rnd = Random.nextInt(list.size)
        val i = list[rnd]
        list.removeAt(rnd)
        val value = this[i]
        lastResult = action(i, value)
    }

}

fun Double.asDecimalStr(precision: Int) = "%.${precision}f".format(this)