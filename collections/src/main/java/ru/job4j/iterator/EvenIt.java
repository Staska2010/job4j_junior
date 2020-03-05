package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator {
    private int cursor = -1;
    private int[] array;

    public EvenIt(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return nextEven() != -1;
    }

    @Override
    public Object next() {
        cursor = nextEven();
        if (cursor == -1) {
            throw new NoSuchElementException();
        }
        return array[cursor];
    }

    private int nextEven() {
        int nextPosition = -1;
        for (int i = cursor + 1; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                nextPosition = i;
                break;
            }
        }
        return nextPosition;
    }
}
