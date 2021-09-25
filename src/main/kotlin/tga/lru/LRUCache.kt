package tga.lru

class LRUCache(val capacity: Int) {

    private class Node(var key: Int = -1, var value: Int = -1, var next: Node? = null){
        override fun toString(): String {
            return "[$value -> ${next?.value ?: "." }]"
        }
    }


    private var head = Node()
    private val index = HashMap<Int, Node>() // key -> Node(value, ...)

    operator fun set(key: Int, value: Int) = put(key, value)

    operator fun get(key: Int): Int {
        val parentNode = index[key] ?: return -1

        val keyNode = parentNode.next!!

        parentNode.next = keyNode.next
        keyNode.next = head.next
        head.next = keyNode

        index[key] = head
        if (keyNode.next != null) index[keyNode.next!!.key] = keyNode

        return keyNode.value
    }


    fun put(key: Int, value: Int) {
        val keyNode = head
        keyNode.key = key
        keyNode.value = value

        head = Node(next = keyNode)
        index[key] = head
    }



}

