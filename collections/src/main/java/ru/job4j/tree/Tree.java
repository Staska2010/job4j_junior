package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * Simple structure of Tree
 *
 * @author
 */

public class Tree<E extends Comparable<E>> implements SimpleTree<E> {
    Node<E> root;
    int modCount = 0;

    public Tree(E rootValue) {
        root = new Node<>(rootValue);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (!findBy(child).isPresent()) {
            findBy(parent).ifPresent(foundNode -> foundNode.add(new Node<>(child)));
            modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Overrided method - searching for a Node in Tree by its value.
     *
     * @param value - target value.
     * @return - Node with searched value, if it is present.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(x -> x.eqValue(value));
    }

    /**
     * Function for determining whether a tree is binary.
     * If the number of descendants of any node doesn't exceed 2
     * then tree is binary
     *
     * @return true of tree is binary, or false in other cases.
     */
    public boolean isBinary() {
        return !(findByPredicate(x -> x.leaves().size() > 2)).isPresent();
    }

    /**
     * Standard method of BSF.
     *
     * @param pr - condition of search as a predicate function.
     * @return Node that satisfies the search condition, Optional type.
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> pr) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (pr.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Queue<Node<E>> nodes = new LinkedList<>();

            {
                nodes.offer(root);
            }

            @Override
            public boolean hasNext() {
                checkModCount();
                return !nodes.isEmpty();
            }

            @Override
            public E next() {
                Node<E> nextNode = nodes.poll();
                if (nextNode != null) {
                    nodes.addAll(nextNode.leaves());
                }
                return nextNode.getValue();
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
