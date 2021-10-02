package tga.colories

import java.util.*
import kotlin.random.Random




fun main() {

    val products = arrayOf("Апельсиновый_сок", "Овсянная_каша", "Изюм", "Сосиски_молочные", "Гречневая_каша", "Йогурт", "Сыр", "Хлеб", "Томаты", "Зефир", "Яблоко", "Капуста_цветная", "Яйца")

    calculateAcceptableVectorLength(
         initialWeight = 100,
         values      = arrayOf(36,    93,  285,  260,  137,  87,  350,  210,    17 , 295,    48,   30,  153),
         x           = arrayOf(0.9,  3.2,  2.5, 11.3,  4.5, 0.5,  24.1,  4.7,  0.7,  0.7,   0.5,  2.7, 12.7),
         y           = arrayOf(0.1,  1.8,  0.0, 23.9,  1.6, 3.2,  29.8,  0.6,  0.0,  0.0,   0.0,  0.0, 11.1),
         z           = arrayOf(8.4, 15.4, 71.4,  1.1, 27.4, 8.0,   0.4, 49.5,  4.1, 77.3,  11.4,  5.2,  0.6),
         xK = 30.0,
         yK = 30.0,
         zK = 40.0
    )


}

fun calculateAcceptableVectorLength(values: Array<Int>, initialWeight: Int, x: Array<Double>, y: Array<Double>, z: Array<Double>, xK: Double, yK: Double, zK: Double) {

    val targetLine = Line(p0 = Vec(0.0,0.0,0.0), v = Vec(xK, yK, zK))

    val initialVectors = Array<Vec>(x.size){ i -> Vec(x[i], y[i], z[i]) }
    val vectors = Array<Vec>(x.size){ i -> Vec(x[i], y[i], z[i]) }
    val weights = Array(x.size){ initialWeight }

    var distance = targetLine.distanceToPoint( vectors.sum() )


    fun performSearchIterations( maxIterationsNumber: Int,
        logPrefix: String,
        acceptableMinDistance: Double,
        changeCandidates: Array<Double>,
        acceptedWeightsRange: IntRange
    ) {
        var iteration = 0; while (iteration < maxIterationsNumber && distance > acceptableMinDistance) {
            iteration++

            vectors.iterateIndexedRandomlyButOnceWhile { i, vec ->


                val vecWeight = weights[i]
                var minDistanceToTargetLine: Double = targetLine.distanceToPoint(vectors.sum())
                var bestK: Double = 0.0
                var corrected = false

                for (kCandidate in changeCandidates) {
                    val weightCandidate:Int = (vecWeight * kCandidate).toInt()


                    if (weightCandidate in acceptedWeightsRange) {
                        val coefficient: Double = weightCandidate.toDouble() / initialWeight.toDouble()
                        val vecCandidate = initialVectors[i] * coefficient

                        val currentVec = vectors[i]
                        vectors[i] = vecCandidate
                        val newDistance = targetLine.distanceToPoint(vectors.sum())

                        if ( newDistance < minDistanceToTargetLine ) {
                            corrected = true
                            minDistanceToTargetLine = newDistance
                            weights[i] = weightCandidate
                            bestK = kCandidate
                        } else {
                            vectors[i] = currentVec
                        }
                    }
                }

                distance = targetLine.distanceToPoint( vectors.sum() )

                if (corrected) {
                    print("$logPrefix :: ${iteration.toString().padStart(2)} > ${i.toString().padStart(3)} -> $vec(${vecWeight.toString().padStart(3)}) --> ")
                    println(" k=${bestK.toString().padStart(4)}  ${vectors[i]}(${weights[i].toString().padStart(3)})   >   distance = ${distance.asDecimalStr(4).padStart(9)}" +
                            "  |  koefs = ${(vectors.sum().procentize() * 100.0).toStr(2,1)}" +
                            "  |  weight = ${weights.sum()}")
                }


                (distance > acceptableMinDistance)
            }
        }
    }

/*
    performSearchIterations( 5,
        logPrefix = "brute",
        acceptableMinDistance =  5.0,
        changeCandidates      = arrayOf( -0.75, -0.50, -0.25, 0.25, 0.50, 0.75 ),
        acceptedWeightsRange = 10..500
    )
*/


    performSearchIterations( 50,
        logPrefix = "accurate",
        acceptableMinDistance =  0.1,
        changeCandidates      = arrayOf( -0.01, -0.03, -0.07, -0.09, 0.09, 0.07, 0.03, 0.01 ),
        acceptedWeightsRange  = 5..600
    )

}

