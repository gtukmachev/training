package tga.training.brackets

fun main() {

    test("([{}])", true)
    test("([{])", false)

}

fun test(input: String, expectedResult: Boolean) {
    val task = "$input -> $expectedResult"
    val actualResult = isBalanced(input)

    if (actualResult == expectedResult) {
        println("\u2713 : $task"); return
    }

    println("\u2717 : $task   | actual result: $actualResult")

}

fun isBalanced(str: String): Boolean {
    val stack = Array(str.length){' '}
    var i = 0

    str.forEach{ ch ->
        when (ch) {
            '(', '[', '{' -> stack[i++] = ch
            ')', ']', '}' -> {
                if (i == 0) return false
                val machSym = when (stack[--i]) {
                    '(' -> ')'
                    '[' -> ']'
                    '{' -> '}'
                    else -> '_'
                }
                if (ch != machSym) return false
            }
            else -> return false
        }
    }

    return i == 0
}
