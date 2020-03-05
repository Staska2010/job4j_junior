package ru.job4j.list;

/**
 * Floyd's cycle search algorithm
 *
 * @param <E>
 */
public class DetermineCycle<E> {
    public boolean hasCycle(Node<E> first) {
        boolean result = false;
        Node<E> slow = first;
        Node<E> fast = first;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                result = true;
                break;
            }
        }
        return result;
    }
}
