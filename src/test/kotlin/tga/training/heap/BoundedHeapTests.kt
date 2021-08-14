package tga.training.heap

import org.assertj.core.api.Assertions.*
import org.junit.Test

// main one


class BoundedHeapTests {

    @Test fun `basic operations min bounded heap`() {
        val heap = BoundedHeap<Int>(7)

        assertThat(heap.size).isEqualTo(0)

        heap.add(90); assertThat(heap.size).isEqualTo(1); assertThat(heap.head).isEqualTo(90)
        heap.add(20); assertThat(heap.size).isEqualTo(2); assertThat(heap.head).isEqualTo(20)
        heap.add(15); assertThat(heap.size).isEqualTo(3); assertThat(heap.head).isEqualTo(15)
        heap.add(30); assertThat(heap.size).isEqualTo(4); assertThat(heap.head).isEqualTo(15)
        heap.add(50); assertThat(heap.size).isEqualTo(5); assertThat(heap.head).isEqualTo(15)
        heap.add(10); assertThat(heap.size).isEqualTo(6); assertThat(heap.head).isEqualTo(10)
        heap.add(70); assertThat(heap.size).isEqualTo(7); assertThat(heap.head).isEqualTo(10)
        heap.add(40); assertThat(heap.size).isEqualTo(7); assertThat(heap.head).isEqualTo(10)
        heap.add( 5); assertThat(heap.size).isEqualTo(7); assertThat(heap.head).isEqualTo( 5)

        assertThat(heap.remove()).isEqualTo( 5); assertThat(heap.size).isEqualTo(6)
        assertThat(heap.remove()).isEqualTo(10); assertThat(heap.size).isEqualTo(5)
        assertThat(heap.remove()).isEqualTo(15); assertThat(heap.size).isEqualTo(4)
        assertThat(heap.remove()).isEqualTo(20); assertThat(heap.size).isEqualTo(3)
        assertThat(heap.remove()).isEqualTo(30); assertThat(heap.size).isEqualTo(2)
        assertThat(heap.remove()).isEqualTo(40); assertThat(heap.size).isEqualTo(1)
        assertThat(heap.remove()).isEqualTo(50); assertThat(heap.size).isEqualTo(0)

        assertThatThrownBy{ heap.remove() }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThat(heap.size).isEqualTo(0)

        assertThatThrownBy{ heap.head }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThat(heap.size).isEqualTo(0)
    }

    @Test fun `basic operations max heap`() {
        val heap = ClassicHeap<Int>{ o1, o2 -> o2 - o1 }

        assertThat(heap.size).isEqualTo(0)

        heap.add(20); assertThat(heap.size).isEqualTo(1); assertThat(heap.head).isEqualTo(20)
        heap.add(15); assertThat(heap.size).isEqualTo(2); assertThat(heap.head).isEqualTo(20)
        heap.add(30); assertThat(heap.size).isEqualTo(3); assertThat(heap.head).isEqualTo(30)
        heap.add(50); assertThat(heap.size).isEqualTo(4); assertThat(heap.head).isEqualTo(50)
        heap.add(10); assertThat(heap.size).isEqualTo(5); assertThat(heap.head).isEqualTo(50)
        heap.add(90); assertThat(heap.size).isEqualTo(6); assertThat(heap.head).isEqualTo(90)
        heap.add(70); assertThat(heap.size).isEqualTo(7); assertThat(heap.head).isEqualTo(90)

        assertThat(heap.remove()).isEqualTo(90); assertThat(heap.size).isEqualTo(6)
        assertThat(heap.remove()).isEqualTo(70); assertThat(heap.size).isEqualTo(5)
        assertThat(heap.remove()).isEqualTo(50); assertThat(heap.size).isEqualTo(4)
        assertThat(heap.remove()).isEqualTo(30); assertThat(heap.size).isEqualTo(3)
        assertThat(heap.remove()).isEqualTo(20); assertThat(heap.size).isEqualTo(2)
        assertThat(heap.remove()).isEqualTo(15); assertThat(heap.size).isEqualTo(1)
        assertThat(heap.remove()).isEqualTo(10); assertThat(heap.size).isEqualTo(0)

        assertThatThrownBy{ heap.remove() }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThat(heap.size).isEqualTo(0)

        assertThatThrownBy{ heap.head }.isInstanceOf(IndexOutOfBoundsException::class.java)
        assertThat(heap.size).isEqualTo(0)
    }

}