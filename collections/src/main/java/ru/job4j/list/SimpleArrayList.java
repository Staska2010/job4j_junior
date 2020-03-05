package ru.job4j.list;

public class SimpleArrayList<E> {
    private int size;
    private Node<E> first;

    /**
     * Insert new Node to List
     *
     * @param data - data to insert
     */
    public void add(E data) {
        Node<E> newLink = new Node(data);
        newLink.next = first;
        first = newLink;
        size++;
    }

    /**
     * Delete first Node of List
     **/
    public E delete() {
        E result = first.data;
        first = first.next;
        size--;
        return result;
    }

    /**
     * Get the size of List
     *
     * @return size of List
     */

    public int getSize() {
        return size;
    }

    /**
     * Get the data from Node by its index
     *
     * @param index - index of Node
     * @return E data
     */
    public E get(int index) {
        Node<E> result = first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    /**
     * Inner class - nodes of List
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
