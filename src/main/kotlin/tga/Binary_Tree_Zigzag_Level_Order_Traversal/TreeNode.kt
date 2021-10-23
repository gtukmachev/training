package tga.Binary_Tree_Zigzag_Level_Order_Traversal

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

     override fun toString(): String {
          return "{${`val`} l:${left?.`val`} r:${right?.`val`}}"

     }
}
