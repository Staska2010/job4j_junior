package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Simple Map realisation
 * does not support key collision
 * does not support inserting null keys
 *
 * @param <K>
 * @param <V>
 */

public class SimpleMap<K, V> implements Iterable {
    //CheckStyle Plugin doesn't accept DEFAULT_INITIAL_CAPACITY
    private final int defaultInitialCapacity = 16;
    private final double loadFactor = 0.75;
    Node<K, V>[] table;
    int tableLength = 0;
    int binCount = 0;
    int modCount = 0;

    /**
     * Simple Node class - bins(buckets) for Map
     *
     * @param <K> key
     * @param <V> value
     */
    private static class Node<K, V> {
        final int hash;
        final K key;
        V value;

        private Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        int getHash() {
            return hash;
        }
    }

    /**
     * SimpleMap constructor
     * default length - 16 bins
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public SimpleMap() {
        table = (Node<K, V>[]) new Node[defaultInitialCapacity];
        tableLength = defaultInitialCapacity;
    }

    /**
     * Insert new pair key-value to Map
     * Key must be non-null
     * If number of bins exceeds length of table * load factor
     * then resize table
     *
     * @param key
     * @param value
     * @return
     */
    public boolean insert(K key, V value) {
        if ((table == null) || (binCount >= tableLength * loadFactor)) {
            tableResize();
        }
        if (key != null) {
            int newNodeHash = hash(key);
            int index = getIndex(key, tableLength);
            if (table[index] == null || (table[index].hash == newNodeHash) && (table[index].key.equals(key))) {
                table[index] = new Node<>(newNodeHash, key, value);
                binCount++;
                modCount++;
                return true;
            }
        }
        return false;
    }

    /**
     * Resize table
     * The length of table grows twice
     * Indexes of bins ore recalculating
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private void tableResize() {
        int newLength = tableLength << 1;
        Node<K, V>[] resizedTable = (Node<K, V>[]) new Node[newLength];
        for (int i = 0; i < tableLength; i++) {
            if (table[i] != null) {
                int newIndex = getIndex(table[i].key, newLength);
                resizedTable[newIndex] = table[i];
            }
        }
        tableLength = newLength;
        table = resizedTable;
    }

    /**
     * get value by key
     *
     * @param key
     * @return value
     */
    public V get(K key) {
        if (key != null) {
            int index = getIndex(key, tableLength);
            if (table != null && table[index] != null) {
                return table[index].value;
            }
        }
        return null;
    }

    /**
     * Delete Node by key
     *
     * @param key - key of Node to delete
     * @return true if removal was successful
     */

    public boolean delete(K key) {
        if (key != null) {
            int index = getIndex(key, tableLength);
            if (table != null && table[index] != null) {
                table[index] = null;
                tableLength--;
                return true;
            }
        }
        return false;
    }

    /**
     * Calculate index of Map table by key
     *
     * @param key
     * @return index of table
     */
    private int getIndex(K key, int length) {
        int hash = hash(key);
        return hash & (length - 1);
    }

    /**
     * Get size of inner table
     *
     * @return size of table
     */
    public int getSize() {
        return tableLength;
    }

    /**
     * Calculate hash of key
     * default value of Object.hashCode(), shifted to get higher bits
     *
     * @param key
     * @return
     */
    static int hash(Object key) {
        int hashCode = key.hashCode();
        return hashCode ^ (hashCode >>> 16);
    }

    @Override
    public Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {
            int index = 0;
            int expectedModCount = modCount;
            Node<K, V> current = nextNode();
            Node<K, V> next = current;

            @Override
            public boolean hasNext() {
                checkForComodification();
                return next != null;
            }

            @Override
            public Node<K, V> next() {
                current = next;
                next = nextNode();
                return current;
            }

            private Node<K, V> nextNode() {
                checkForComodification();
                if ((table != null) || tableLength > 0) {
                    while (index < tableLength) {
                        if (table[index] != null) {
                            return table[index++];
                        }
                        index++;
                    }
                }
                return null;
            }

            private void checkForComodification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
