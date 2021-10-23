package tga.Reorder_Log_Files

import java.util.*

class Solution {

    private fun compareStringsPartially(a: String, aFrom: Int, aTo: Int, b: String, bFrom: Int, bTo: Int): Int {
        var ia = aFrom; var ib = bFrom; while (ia < aTo && ib < bTo) {
            val charA = a[ia++]; val charB = b[ib++]
            if (charA != charB) return charA - charB
        }

        return (aTo - aFrom) - (bTo - bFrom)
    }


    private fun compareLogs(a: String, b: String): Int {

        val firstSpaceA = 0.let{ var i = it; while(i < a.length && a[i] != ' ') i++; i }
        val firstSpaceB = 0.let{ var i = it; while(i < b.length && b[i] != ' ') i++; i }

        val resultByTails = compareStringsPartially(
            a, firstSpaceA+1, a.length,
            b, firstSpaceB+1, b.length
        )

        if (resultByTails != 0) return resultByTails

        return compareStringsPartially(
            a, 0, firstSpaceA,
            b, 0, firstSpaceB
        )
    }

    fun isLetterLog(log: String): Boolean {
        var i = 0;
        while (i < log.length && log[i] != ' ') i++

        return log[i+1] > '9'
    }

    fun reorderLogFiles(logs: Array<String>): Array<String> {

        val heap = PriorityQueue(this::compareLogs)

        var j = logs.size-1

        for (i in logs.size-1 downTo 0) {
            val log = logs[i]
            if (isLetterLog(log)) {
                heap.add(log)
            } else {
                if (i != j) { logs[j--] = log } else { j-- }
            }
        }

        var i = 0; while (heap.isNotEmpty()) logs[i++] = heap.remove()

        return logs
    }

}
