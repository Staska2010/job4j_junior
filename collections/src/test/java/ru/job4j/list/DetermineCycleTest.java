package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DetermineCycleTest {
    DetermineCycle<Integer> dc;

    @Before
    public void init() {
        dc = new DetermineCycle<>();
    }

    @Test
    public void whenCheckSimplyCycledListThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = first;
        assertThat(dc.hasCycle(first), is(true));
    }

    @Test
    public void whenCheckListWithCycleInTheMiddleThenTrue() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = two;
        assertThat(dc.hasCycle(first), is(true));
    }

    @Test
    public void whenTestNotCycledListThenFalse() {
        Node first = new Node(1);
        Node two = new Node(2);
        Node third = new Node(3);
        Node four = new Node(4);

        first.next = two;
        two.next = third;
        third.next = four;
        four.next = null;
        assertThat(dc.hasCycle(first), is(false));
    }

    @Test
    public void whenJustOneElementCycledAtItselfThenTrue() {
        Node first = new Node(1);
        first.next = first;
        assertThat(dc.hasCycle(first), is(true));
    }

    @Test
    public void whenJustOneElementNotCycledThenFalse() {
        Node first = new Node(1);
        first.next = null;
        assertThat(dc.hasCycle(first), is(false));
    }

    @Test
    public void whenNullThenFalse() {
        Node first = new Node(null);
        assertThat(dc.hasCycle(first), is(false));
    }
}
