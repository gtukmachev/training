package tga.Binary_Tree_Zigzag_Level_Order_Traversal

import java.util.*

class Solution {

    fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return emptyList()

        var inputStack  = ArrayList<TreeNode>()
        var outputStack = ArrayList<TreeNode>(); outputStack.add(root)

        val result: MutableList<List<Int>> = LinkedList()

        var isFromLeftToRight = false

        while(outputStack.isNotEmpty()) {
            val level: MutableList<Int> = LinkedList()
            val tmp = inputStack; inputStack = outputStack; outputStack = tmp
            isFromLeftToRight = !isFromLeftToRight

            while(inputStack.isNotEmpty()) {
                val node = inputStack.removeLast()
                level.add(node.`val`)
                if (isFromLeftToRight) {
                    if (node.left  != null) outputStack.add(node.left!!)
                    if (node.right != null) outputStack.add(node.right!!)
                } else {
                    if (node.right != null) outputStack.add(node.right!!)
                    if (node.left  != null) outputStack.add(node.left!!)
                }
            }
            result.add(level)
        }

        return result
    }
}