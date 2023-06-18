package liteweb.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: sinar
 * 2023/6/18 22:33
 */
public class SortedByUsageLinkedListTest {

    @Test
    public void shouldEmpty_WhenListIsEmpty() {
        var list = new SortedByUsageLinkedList<String>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void shouldNotEmpty_WhenListNotEmpty() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        assertFalse(list.isEmpty());
    }

    @Test
    public void shouldEmpty_WhenAddAndRemoveElement() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        list.removeOldest();
        assertTrue(list.isEmpty());
    }

    @Test
    public void verifyRemoveOldest() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        list.addToFront("bar");
        list.addToFront("baz");
        assertEquals("foo", list.removeOldest().getValue());
        assertEquals(2, list.size());
    }

    @Test
    public void verifyMoveToFront() {
        var list = new SortedByUsageLinkedList<String>();
        var foo = list.addToFront("foo");
        list.addToFront("bar");
        list.addToFront("baz");

        var newNode = list.moveToFront(foo);

        assertEquals(newNode, list.getHead());
        assertEquals("foo", list.getHead().getValue());
        assertEquals("bar", list.getTail().getValue());
    }

    @Test
    public void verifyUpdateToFront() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        var bar = list.addToFront("bar");
        list.addToFront("baz");

        var newNode = list.updateToFront(bar, "qux");

        assertEquals(newNode, list.getHead());
        assertEquals("qux", newNode.getValue());
    }

    @Test
    public void verifyUpdateToFrontOnEmptyNode() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        list.addToFront("bar");
        list.addToFront("baz");

        var newNode = list.updateToFront(new EmptyNode<>(), "qux");

        assertTrue(newNode.isEmpty());
    }

    @Test
    public void verifyDetachHead() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        list.addToFront("bar");
        var baz = list.addToFront("baz");

        list.detach(baz);

        assertEquals("bar", list.getHead().getValue());
    }

    @Test
    public void verifyClean() {
        var list = new SortedByUsageLinkedList<String>();
        list.addToFront("foo");
        list.addToFront("bar");
        list.addToFront("baz");

        list.clean();

        assertTrue(list.isEmpty());
    }
}
