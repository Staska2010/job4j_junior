package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleQueueTest {
    SimpleQueue<Integer> queue;

    @Before
    public void init() {
        queue = new SimpleQueue<>();
    }

    @Test
    public void whenPush1And2And3ThenPoll1And2And3() {
        queue.push(1);
        queue.push(2);
        queue.push(3);
        assertThat(queue.poll(), is(1));
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
    }

    @Test
    public void whenPushedInRandomOrderThenCorrectlyRetrieved() {
        queue.push(1);
        queue.push(2);
        assertThat(queue.poll(), is(1));
        queue.push(3);
        queue.push(4);
        assertThat(queue.poll(), is(2));
        assertThat(queue.poll(), is(3));
        queue.push(5);
        assertThat(queue.poll(), is(4));
        assertThat(queue.poll(), is(5));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenPollFromEmptyQueueThenException() {
        queue.push(1);
        queue.poll();
        queue.poll();
    }
}
