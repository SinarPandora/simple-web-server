package liteweb.cache;

import org.jetbrains.annotations.NotNull;

/**
 * Empty linked list node
 * Author: sinar
 * 2023/6/18 16:44
 */
public class EmptyNode<T> extends Node<T> {
    @Override
    public Node<T> getPrev() {
        return this;
    }

    @Override
    public void setPrev(@NotNull Node<T> prev) {
    }

    @Override
    public Node<T> getNext() {
        return this;
    }

    @Override
    public void setNext(@NotNull Node<T> next) {
    }

    @Override
    public T getValue() {
        throw new NullPointerException("Cannot get value from empty node");
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public void setValue(@NotNull T value) {
        super.setValue(value);
    }

    @Override
    public void detach() {

    }

    @Override
    public Node<T> search(@NotNull T value) {
        return this;
    }
}
