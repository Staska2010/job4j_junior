package ru.job4j.generic;

import java.util.Iterator;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] array;
    private int endPointer = 0;

    public SimpleArray(int size) {
        array = new Object[size];
    }

    public void add(T model) {
        if (endPointer >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[endPointer++] = model;
    }

    public boolean set(int index, T model) {
        if (index < 0 || index > endPointer - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
        return true;
    }

    public int getLength() {
        return endPointer;
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, --endPointer - index);
        array[endPointer] = null;
    }

    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int itCursor = 0;
            @Override
            public boolean hasNext() {
                return itCursor < endPointer;
            }

            @Override
            public T next() {
                return (T) array[itCursor++];
            }
        };
    }
}
