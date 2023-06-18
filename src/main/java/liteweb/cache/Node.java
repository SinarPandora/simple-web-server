package liteweb.cache;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * LinkedList Node
 * Author: sinar
 * 2023/6/18 11:41
 */
public class Node<T> {
    private Node<T> prev;
    private Node<T> next;
    private T value;

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(@NotNull Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(@NotNull Node<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public boolean isEmpty() {
        return false;
    }

    public void setValue(@NotNull T value) {
        this.value = value;
    }

    public void detach() {
        this.prev.setNext(this.next);
        this.next.setPrev(this.prev);
    }

    public Node<T> search(@NotNull T value) {
        return Objects.equals(this.getValue(), value) ? this : this.next.search(value);
    }
}
