package tga.binary_tree.serilize

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TreeNodeCodecTests {

    val codec = Codec()

    @Test
    fun treeDesTestNull() {
        val tree = codec.deserialize("")
        assertThat(tree).isNull()
    }

    @Test
    fun treeDesTest1() {
        val tree = codec.deserialize("1")!!

        assertThat(tree.`val`).isEqualTo(1)
        assertThat(tree.left).isNull()
        assertThat(tree.right).isNull()

    }

    @Test
    fun treeDesTest123() {
        val tree = codec.deserialize("1[2,3]")!!

        assertThat(tree.`val`).isEqualTo(1)
        assertThat(tree.left).isNotNull()
        assertThat(tree.right).isNotNull()

        var node = tree.left!!
        assertThat(node.`val`).isEqualTo(2)
        assertThat(node.left).isNull()
        assertThat(node.right).isNull()

        node = tree.right!!
        assertThat(node.`val`).isEqualTo(3)
        assertThat(node.left).isNull()
        assertThat(node.right).isNull()

    }

    @Test
    fun treeDesTestLong() {
        val tree = codec.deserialize("1[11[111,112],12[,122]]")!!

        var node = tree
        assertThat(node.`val`).isEqualTo(1)

        node = tree.left!!
        assertThat(node.`val`).isEqualTo(11)

        var subnode = node.left!!
        assertThat(subnode.`val`).isEqualTo(111)
        assertThat(subnode.left).isNull()
        assertThat(subnode.right).isNull()

        subnode = node.right!!
        assertThat(subnode.`val`).isEqualTo(112)
        assertThat(subnode.left).isNull()
        assertThat(subnode.right).isNull()


        node = tree.right!!
        assertThat(node.`val`).isEqualTo(12)
        assertThat(node.left).isNull()
        assertThat(node.right).isNotNull()
        assertThat(node.right!!.`val`).isEqualTo(122)
        assertThat(node.right!!.left).isNull()
        assertThat(node.right!!.right).isNull()

    }


}
