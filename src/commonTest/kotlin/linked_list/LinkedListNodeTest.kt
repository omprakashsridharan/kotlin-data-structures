package linked_list

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class LinkedListTest {
    @Test
    fun testIntNodeCreation() {
        val n1 = Node<Int>(2)
        assertEquals(n1.value, 2)
    }

    @Test
    fun testStringNodeCreation() {
        val n1 = Node<String>("Hello")
        assertEquals(n1.value, "Hello")
    }

    @Test
    fun testNodeToStringSingleNode() {
        val n1 = Node<Int>(2)
        assertEquals(n1.toString(), "2")
    }

    @Test
    fun testNodeToStringMultiNode() {
        val n1 = Node<Int>(1)
        val n2 = Node<Int>(2)
        val n3 = Node<Int>(3)
        n1.next = n2
        n2.next = n3
        assertEquals(n1.toString(), "1 -> 2 -> 3")
    }

    @Test
    fun testEmptyLinkedList() {
        val linkedList = LinkedList<Int>()
        assertTrue(linkedList.isEmpty())
    }

    @Test
    fun testLinkedListToStringEmpty() {
        val linkedList = LinkedList<Int>()
        assertEquals(linkedList.toString(), "EMPTY LIST")
    }

    @Test
    fun testLinkedListPush() {
        val linkedList = LinkedList<Int>()
        linkedList.push(1)
        linkedList.push(2)
        assertEquals(linkedList.isEmpty(), false)
        assertEquals(linkedList.toString(), "2 -> 1")
    }

    @Test
    fun testLinkedListAppend() {
        val linkedList = LinkedList<Int>()
        linkedList.append(1)
        linkedList.append(2)
        assertEquals(linkedList.isEmpty(), false)
        assertEquals(linkedList.toString(), "1 -> 2")
    }

    @Test
    fun testLinkedListPlus() {
        val linkedList = LinkedList<Int>()
        linkedList + 1
        linkedList + 2
        assertEquals(linkedList.isEmpty(), false)
        assertEquals(linkedList.toString(), "1 -> 2")
    }

    @Test
    fun testLinkedListInsert() {
        val linkedList = LinkedList<Int>()
        linkedList + 1
        linkedList + 29
        linkedList + 332
        linkedList + 21
        linkedList.insert(2, 200)
        assertEquals(linkedList.toString(), "1 -> 29 -> 200 -> 332 -> 21")
    }

    @Test
    fun testLinkedListPop() {
        val linkedList = LinkedList<Int>()
        linkedList + 10
        linkedList + 29
        assertEquals(linkedList.pop(), 10)
    }

    @Test
    fun testLinkedListNodeAt() {
        val linkedList = LinkedList<Int>()
        linkedList + 10
        linkedList + 29
        assertEquals(linkedList.nodeAt(1)?.value, 29)
    }

    @Test
    fun testLinkedListIterator() {
        val linkedList = LinkedList<Int>()
        linkedList + 10
        linkedList + 29
        var sum = 0
        for (n in linkedList) {
            sum += n
        }
        assertEquals(sum, 39)
    }

    @Test
    fun testLinkedListContains() {
        val linkedList = LinkedList<Int>()
        linkedList + 10
        linkedList + 29
        assertTrue(linkedList.contains(10))
    }

    @Test
    fun testLinkedListContainsAll() {
        val linkedList = LinkedList<Int>()
        linkedList + 10
        linkedList + 29
        assertTrue(linkedList.containsAll(listOf(10, 29)))
    }

    @Test
    fun testLinkedListContainsAllFailure() {
        val linkedList = LinkedList<Int>()
        linkedList + 10
        linkedList + 29
        assertTrue(!linkedList.containsAll(listOf(11, 29)))
    }
}