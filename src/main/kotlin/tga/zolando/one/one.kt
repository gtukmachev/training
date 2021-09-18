package tga.zolando.one

// you can also use imports, for example:
// import kotlin.math.*

// you can write to stdout for debugging purposes, e.g.
// println("this is a debug message")

fun main() {

    solution("50552")

}

fun solution(S: String): Int {
    if (S.length == 2) return S.toInt()

    var iMax = 0

    for (i in 2 .. (S.length -2) ) {

        if (S[i] > S[iMax]) {
            iMax = i
            println("$i: iMax <- $iMax (first)")
        } else if (S[i] == S[iMax]){
            if (S[i+1] > S[iMax+1]) {
                iMax = i
                println("$i: iMax <- $iMax (second)")
            }
        }

    }

    println("> Max = $iMax")
    val maxSt = S.substring(iMax, iMax+2)

    return maxSt.toInt()
}
