package ru.job4j.list;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicContainer<E> implements Iterable<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    /**
     * Default constructor
     */
    public DynamicContainer() {
        container = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * Constructor with initial capacity of container
     *
     * @param initialCapacity
     */
    public DynamicContainer(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        container = new Object[initialCapacity];
    }

    /**
     * add E element to container
     *
     * @param value - value of element
     */
    public boolean add(E value) {
        modCount++;
        ensureCapacity();
        container[size++] = value;
        return true;
    }

    /**
     * checks if container length enough to store next element
     * if not, length of container grows twice
     */
    private void ensureCapacity() {
        if (size >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
    }

    /**
     * Get data from i position of container
     *
     * @param index - position of element to extract
     * @return value of element
     */

    public E get(int index) {
        return (E) container[index];
    }

    /**
     * @return length of container
     */
    public int getLength() {
        return container.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int index = 0;
            private int itModCount = modCount;

            @Override
            public boolean hasNext() {
                return index < size ? true : false;
            }

            @Override
            public E next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[index++];
            }

            private void checkModCount() {
                if (itModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
