package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleLinkedListTest {
    SimpleLinkedList<Integer> list;

    @Before
    public void init() {
        list = new SimpleLinkedList<>();
    }

    @Test
    public void whenAddElementsToEmptyListTheyAreInList() {
        list.add(1);
        assertThat(list.get(0), is(1));
        list.add(2);
        assertThat(list.get(0), is(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetElementFromEmptyListThenExceprion() {
        list.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetNonExistingElementThenExceprion() {
        list.add(1);
        list.get(1);
    }

    @Test
    public void whenUsingIteratorHasNextWorksCorrectly() {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenUsingIteratorDataRetrievesInRightOrder() {
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextOnLastElementThenException() {
        list.add(1);
        Iterator<Integer> it = list.iterator();
        it.next();
        it.next();
    }
}
