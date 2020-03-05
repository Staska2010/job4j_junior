package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Linked List implementaion
 */
public class SimpleLinkedList<E> implements Iterable {
    int listLength = 0;
    private Node<E> head = null;
    private Node<E> tail = null;
    private int modCount = 0;

    /**
     * Add node to list
     *
     * @param data - data to store in node
     * @return true if everything is Ok
     */
    public boolean add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        listLength++;
        modCount++;
        return true;
    }

    /**
     * Data retrieving from node at index position
     *
     * @param index - index of node for data retrieving
     * @return data
     */
    public E get(int index) {
        Node<E> searchedNode = head;
        if (index < 0 || index >= listLength) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            searchedNode = searchedNode.next;
        }
        return searchedNode.data;
    }

    public E removeLast() {
        E result = tail.data;
        if (head == null) {
            throw new NoSuchElementException();
        }
        if (tail == head) {
            tail = null;
            head = null;
        } else {
            tail = head;
            for (int i = 0; i < listLength - 2; i++) {
                tail = tail.next;
            }
            tail.next = null;
        }
        listLength--;
        return result;
    }

    /**
     * Implementation of Iterator for linked list
     *
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> currentNode = head;
            private Node<E> previousNode = null;
            private Node<E> tempNode = null;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkForComodification();
                return currentNode != null;
            }


            @Override
            public E next() {
                E result;
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                result = currentNode.data;
                tempNode = previousNode;
                previousNode = currentNode;
                currentNode = currentNode.next;
                return result;
            }

            @Override
            public void remove() {
                if (head == null) {
                    throw new NoSuchElementException();
                }
                checkForComodification();
                if (tempNode == null) {
                    head = previousNode.next;
                } else {
                    tempNode.next = previousNode.next;
                    tail = tempNode;
                }
                listLength--;
            }

            /**
             * check if count of modifications changed since Iterator was created
             */
            private void checkForComodification() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
