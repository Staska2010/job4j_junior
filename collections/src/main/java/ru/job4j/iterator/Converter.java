package ru.job4j.iterator;

import java.util.*;

public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> its) {
        return new Iterator<Integer>() {
            private Iterator<Integer> cursor = its.next();

            @Override
            public boolean hasNext() {
                cursor = selectNextIterator();
                return cursor.hasNext() && cursor != null;
            }

            @Override
            public Integer next() {
                cursor = selectNextIterator();
                if (cursor == null) {
                    throw new NoSuchElementException();
                }
                return cursor.next();
            }

            private Iterator<Integer> selectNextIterator() {
                Iterator<Integer> nextIterator = null;
                if (cursor.hasNext()) {
                    nextIterator = cursor;
                } else {
                    while (!cursor.hasNext() && its.hasNext()) {
                        cursor = its.next();
                    }
                    nextIterator = cursor;
                }
                return nextIterator;
            }
        };
    }
}