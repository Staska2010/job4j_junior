package ru.job4j.list;

public class Node<E> {
    Node<E> next;
    E value;

    Node(E value) {
        this.value = value;
    }
}
