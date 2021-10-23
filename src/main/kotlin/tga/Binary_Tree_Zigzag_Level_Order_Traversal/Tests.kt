package tga.Binary_Tree_Zigzag_Level_Order_Traversal

import org.assertj.core.api.Assertions
import org.junit.Test

class Tests {
    val s = Solution()

    @Test fun t_1_32_45() = tst(
        TreeNode(1).apply {
            left = TreeNode(2).apply {
                left = TreeNode(4)
            }
            right = TreeNode(3).apply {
                right = TreeNode(5)
            }
        },
        listOf(
            listOf(1),
            listOf(3,2),
            listOf(4,5),
        )

    )

    private fun tst(root: TreeNode, expectedOutput: List<List<Int>>) {
        val output = s.zigzagLevelOrder(root)
        Assertions.assertThat(output).isEqualTo(expectedOutput)
    }
}