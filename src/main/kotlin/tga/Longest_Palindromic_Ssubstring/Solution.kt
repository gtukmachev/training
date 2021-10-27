package tga.Longest_Palindromic_Ssubstring

class Solution {
    fun longestPalindrome(s: String): String {
        val LEN = s.length
        if (LEN < 2) return s

        fun growPalindromeAroundCenter(p: IntArray) {
            while (p[0] >= 0 && p[1] < LEN && s[p[0]] == s[p[1]]) {
                p[0]--; p[1]++
            }
            p[0]++; p[1]--
        }

        val jStart = LEN / 2
        val iStart = if ( (LEN and 1) == 1 ) jStart else jStart-1

        var maxPalindromLen = 1
        val maxPalindrom = intArrayOf(0,0)
        val p = IntArray(2)


        var i=iStart; var j=jStart; var iORj = true;
        var direction = false;
        var maxPossiblePalindromLen = LEN

        fun iterateByString() {
            while (maxPalindromLen < maxPossiblePalindromLen) {
                p[0] = i; p[1] = j
                growPalindromeAroundCenter(p)
                val pLen = p[1] - p[0] + 1
                if (pLen > maxPalindromLen) {
                    maxPalindromLen = pLen; maxPalindrom[0] = p[0]; maxPalindrom[1] = p[1]
                    //println("$maxPalindromLen -> ${s.substring(maxPalindrom[0], maxPalindrom[1]+1)}")
                }

                if (direction) { if (iORj) i++ else j++ }
                          else { if (iORj) i-- else j-- }
                maxPossiblePalindromLen--

                iORj = !iORj
            }
        }

        iterateByString()

        i=iStart; j=jStart+1; iORj = true; direction = true
        maxPossiblePalindromLen = LEN-1
        iterateByString()

        return s.substring(maxPalindrom[0], maxPalindrom[1]+1)
    }
}