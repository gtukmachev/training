package tga.lru

class LRUCache(val capacity: Int) {

    private class Node(var key: Int = -1, var value: Int = -1, var next: Node? = null, var prev: Node? = null){
        override fun toString(): String {
            return "[${prev?.value ?: "." } < $value > ${next?.value ?: "." }]"
        }
    }

    var size: Int = 0
        private set

    private var head = Node()                                    // the `head` and `last` are barriers:
    private var last = Node(prev = head).also { head.next = it } // `head` <-> 1 <-> 2 <-> ... <-> `last`

    private val index = HashMap<Int, Node>() // key -> Node(value, ...)

    operator fun set(key: Int, value: Int) = put(key, value)

    operator fun get(key: Int): Int {
        val keyNode = index[key] ?: return -1

        val prevNode = keyNode.prev!!
        val nextNode = keyNode.next!!

        // move the current element to top of the list

        prevNode.next = nextNode
        nextNode.prev = prevNode

        keyNode.next = head.next
        keyNode.prev = head

        head.next!!.prev = keyNode
        head.next = keyNode

        index[key] = keyNode

        return keyNode.value
    }


    fun put(key: Int, value: Int) {
        val existsValue = get(key)
        if (existsValue != -1) {
            head.next!!.value = value
            return
        }

        val keyNode = head
        keyNode.key = key
        keyNode.value = value

        head = Node(next = keyNode)
        keyNode.prev = head

        index[key] = keyNode

        size++

        if (size > capacity) {

            val beforeLast = last.prev!!
            index.remove(beforeLast.key)

            val beforeBeforeLast = last.prev!!.prev!!
            beforeBeforeLast.next = last
            last.prev = beforeBeforeLast

            size--
        }
        return
    }

}

