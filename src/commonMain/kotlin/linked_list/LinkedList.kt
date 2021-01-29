package linked_list

data class Node<T>(var value: T, var next: Node<T>? = null) {
    override fun toString(): String {
        return if (next != null) {
            "$value -> ${next.toString()}"
        } else {
            "$value"
        }
    }
}

class LinkedList<T> : Iterable<T>, Collection<T>, MutableIterable<T>, MutableCollection<T> {
    private var head: Node<T>? = null
    private var tail: Node<T>? = null
    override var size = 0
        private set

    override fun isEmpty() = size == 0

    override fun toString(): String {
        return if (isEmpty()) {
            "EMPTY LIST"
        } else {
            head.toString()
        }
    }

    fun append(value: T) {
        val newNode = Node<T>(value)
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            tail?.next = newNode
            tail = newNode
        }
        size++
    }

    fun push(value: T) {
        val newNode = Node<T>(value)
        if (isEmpty()) {
            head = newNode
            tail = newNode
        } else {
            newNode.next = head
            head = newNode
        }
        size++
    }

    fun insert(index: Int, value: T) {
        if (index < 0 || index >= size) {
            throw Exception("Invalid index")
        }
        if (isEmpty() && index != 0) {
            throw Exception("Invalid index")
        }

        var currentIndex = 0
        var temp = head
        while (currentIndex < size && currentIndex + 1 < index) {
            temp = temp?.next
            currentIndex++
        }
        val newNode = Node(value, temp?.next)
        temp?.next = newNode
    }

    fun pop(): T? {
        return if (isEmpty()) {
            null
        } else {
            val poppedValue = head?.value
            head = head?.next
            size--
            if (isEmpty()) {
                tail = null
            }
            poppedValue
        }
    }

    fun nodeAt(index: Int): Node<T>? {
        var currentNode = head
        var currentIndex = 0
        while (currentNode != null && currentIndex < index) {
            currentNode = currentNode.next
            currentIndex++
        }
        return currentNode
    }

    fun removeAfter(node: Node<T>): T? {
        val result = node.next?.value
        if (node.next == tail) {
            tail = node
        }
        if (node.next != null) {
            size--
        }
        node.next = node.next?.next
        return result
    }

    operator fun plus(value: T) {
        append(value)
    }

    override fun iterator(): MutableIterator<T> {
        return LinkedListIterator(this)
    }

    override fun contains(element: T): Boolean {
        for (item in this) {
            if (item == element) return true
        }
        return false
    }

    override fun containsAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            if (!contains(element)) return false
        }
        return true
    }

    override fun add(element: T): Boolean {
        append(element)
        return true
    }

    override fun addAll(elements: Collection<T>): Boolean {
        for (element in elements) {
            append(element)
        }
        return true
    }

    override fun clear() {
        head = null
        tail = null
        size = 0
    }

    override fun remove(element: T): Boolean {
        val iterator = iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (item == element) {
                iterator.remove()
                return true
            }
        }
        return false
    }

    override fun removeAll(elements: Collection<T>): Boolean {
        var result = false
        for (item in elements) {
            result = remove(item) || result
        }
        return result
    }

    override fun retainAll(elements: Collection<T>): Boolean {
        var result = false
        val iterator = this.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            if (!elements.contains(item)) {
                iterator.remove()
                result = true
            }
        }
        return result
    }


}

class LinkedListIterator<T>(private val list: LinkedList<T>) : MutableIterator<T> {
    private var index = 0
    private var lastNode: Node<T>? = null
    override fun hasNext(): Boolean {
        return index < list.size
    }

    override fun next(): T {
        if (index >= list.size) throw IndexOutOfBoundsException()
        lastNode = if (index == 0) {
            list.nodeAt(0)
        } else {
            lastNode?.next
        }
        index++
        return lastNode!!.value
    }

    override fun remove() {
        if (index == 1) {
            list.pop()
        } else {
            val prevNode = list.nodeAt(index - 2) ?: return
            list.removeAfter(prevNode)
            lastNode = prevNode
        }
        index--
    }


}