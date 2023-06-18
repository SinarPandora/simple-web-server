package liteweb.cache;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Doubly linked list sorted by usage
 * Author: sinar
 * 2023/6/18 11:37
 */
public class SortedByUsageLinkedList<T> {
    private final EmptyNode<T> EMPTY = new EmptyNode<>();
    private Node<T> head = EMPTY;
    private Node<T> tail = EMPTY;
    private final AtomicInteger size = new AtomicInteger(0);
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public boolean isEmpty() {
        lock.readLock().lock();
        try {
            return this.head == null && this.tail == null;
        } finally {
            lock.readLock().unlock();
        }
    }

    public Node<T> addToFront(@NotNull T value) {
        lock.writeLock().lock();
        try {
            Node<T> node = new Node<>();
            node.setValue(value);
            head.setPrev(node);
            node.setNext(head);
            node.setPrev(EMPTY);
            head = node;
            if (tail.isEmpty()) {
                tail = head;
            }
            size.getAndIncrement();
            return node;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Node<T> moveToFront(@NotNull Node<T> node) {
        return this.updateToFront(node, node.getValue());
    }

    public Node<T> updateToFront(@NotNull Node<T> node, @NotNull T newValue) {
        lock.writeLock().lock();
        try {
            if (node.isEmpty()) {
                return EMPTY;
            }
            detach(node);
            addToFront(newValue);
            return head;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public Node<T> removeOldest() {
        lock.writeLock().lock();
        try {
            Node<T> oldTail = tail;
            if (tail == head) {
                tail = head = EMPTY;
            } else {
                tail = tail.getPrev();
                oldTail.detach();
            }
            if (!oldTail.isEmpty()) {
                size.getAndDecrement();
            }
            return oldTail;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void detach(@NotNull Node<T> node) {
        lock.writeLock().lock();
        try {
            if (node != tail) {
                node.detach();
                if (node == head) {
                    head = node.getNext();
                }
            } else {
                removeOldest();
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

    public int size() {
        return size.getAcquire();
    }

    public void clean() {
        lock.writeLock().lock();
        try {
            head = EMPTY;
            tail = EMPTY;
            size.getAndSet(0);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
