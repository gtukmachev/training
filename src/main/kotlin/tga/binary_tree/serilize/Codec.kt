package tga.binary_tree.serilize

import java.util.*


class Codec {

    // Encodes a URL to a shortened URL.
    fun serialize(root: TreeNode?): String {
        return ""
    }

    // Decodes your encoded data to tree.
    fun deserialize(data: String): TreeNode? {

        val stack: Stack<State> = Stack()
        var state = State(TreeNode(-1), 1)
        //stack.push(state)

        data.tokens().forEach { token ->
            when (token) {

                is ValueToken -> {
                    val newNode = TreeNode(token.n)
                    when (state.currentChild) {
                        0 -> { if (state.parent.left  == null) state.parent.left  = newNode else throw RuntimeException("left is already defined -> a ',' expected") }
                        1 -> { if (state.parent.right == null) state.parent.right = newNode else throw RuntimeException("right is already defined -> a '[' or ']' expected") }
                        else -> throw RuntimeException("Only 2 children are supported (value)")
                    }
                }

                is CommaToken -> {
                    state.currentChild++
                }

                is OpenBracket -> {
                    stack.push(state)
                    state = State(
                        parent = when(state.currentChild) {
                                0 -> if (state.parent.left != null)  state.parent.left!!  else throw RuntimeException("parent(left) is not defined - an 'int' expected")
                                1 -> if (state.parent.right != null) state.parent.right!! else throw RuntimeException("parent(right) is not defined - an 'int' expected")
                             else -> throw RuntimeException("Only 2 children are supported (OpenBracket)")
                            },
                    )
                }

                is CloseBracket -> {
                    state = stack.pop()
                }
            }
        }

        return state.parent.right
    }


    fun String.tokens(): Sequence<Token> = TokensSequence(this)


}

class State(val parent: TreeNode, var currentChild: Int = 0)

sealed interface Token
    data class ValueToken(val n: Int): Token
        object OpenBracket: Token { override fun toString() = "OpenBracket" }
        object CloseBracket: Token { override fun toString() = "CloseBracket" }
        object CommaToken: Token { override fun toString() = "CommaToken" }

class TokensSequence(private val input: String?):Sequence<Token> {

    class TokensIterator(val input_: String?): Iterator<Token> {

        val input = input_ ?: ""
        var nextToken: Token? = if (input_.isNullOrEmpty()) null else readNext()
        var i: Int = 0

        override fun hasNext() = nextToken != null

        override fun next(): Token {
            val token = nextToken!!
            nextToken = readNext()
            return token
        }

        private fun readNext(): Token? {
            if (i >= input.length) return null

            val ch = input[i++]

            val token =  when(ch) {
                '[' -> OpenBracket
                ']' -> CloseBracket
                ',' -> CommaToken
                        '-' -> ValueToken(-readInt(0))
                in '0'..'9' -> ValueToken( readInt(ch - '0'))
                else -> throw RuntimeException("Unexpected symbol '$ch' at $i position")
            }

            return token

        }

        private fun readInt(n_: Int): Int {
            var n = n_
            while (i < input.length ) {
                val next = input[i++]
                if (next in '0'..'9') {
                    n = n * 10 + (next - '0')
                } else {
                    i--
                    return n
                }
            }
            return n
        }
    }

    override fun iterator() = TokensIterator(input)

}