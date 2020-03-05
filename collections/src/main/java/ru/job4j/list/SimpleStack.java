package ru.job4j.list;

import java.util.NoSuchElementException;

/**
 * Simple Stack Class, based on Linked List
 */

public class SimpleStack<E> {
    private SimpleLinkedList<E> stack = new SimpleLinkedList<>();

    /**
     * Push value to stack
     *
     * @param value
     */
    public void push(E value) {
        stack.add(value);
    }

    /**
     * extract value from stack
     *
     * @return E type, stored in stack
     */

    public E pop() {
        if (stack.listLength == 0) {
            throw new NoSuchElementException();
        }
        return stack.removeLast();
    }

    public boolean isEmpty() {
        return stack.listLength == 0 ? true : false;
    }
}
