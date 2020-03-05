package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {
    private SimpleArray<Integer> sa;

    @Before
    public void init() {
        sa = new SimpleArray<>(3);
        sa.add(1);
        sa.add(2);
        sa.add(3);
    }

    @Test
    public void whenAddNewModels() {
        assertThat(sa.get(0), is(1));
        assertThat(sa.get(1), is(2));
        assertThat(sa.get(2), is(3));
    }

    @Test
    public void whenRemoveModelElementsShiftsLeft() {
        sa.remove(1);
        assertThat(sa.get(1), is(3));
        assertNull(sa.get(2));
    }

    @Test
    public void whenRemoveLastElementThenOk() {
        sa.remove(2);
        assertNull(sa.get(2));
    }

    @Test
    public void whenSetNewElementThenThisElementIsInArray() {
        boolean out = sa.set(2, 10);
        assertThat(out, is(true));
        assertThat(sa.get(2), is(10));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenSetNewElementOutOfBoundsThenException() {
        boolean out = sa.set(4, 10);
        assertThat(out, is(false));
    }

    @Test
    public void whenUseIteratorItWorksCorrectly() {
        Iterator saIt = sa.iterator();
        assertThat(saIt.hasNext(), is(true));
        assertThat(saIt.next(), is(1));
        assertThat(saIt.hasNext(), is(true));
        assertThat(saIt.next(), is(2));
        assertThat(saIt.hasNext(), is(true));
        assertThat(saIt.next(), is(3));
        assertThat(saIt.hasNext(), is(false));
    }

    @Test
    public void whenUseIteratorAndRemoveElementItWorksCorrectly() {
        sa.remove(1);
        Iterator saIt = sa.iterator();
        assertThat(saIt.hasNext(), is(true));
        assertThat(saIt.next(), is(1));
        assertThat(saIt.hasNext(), is(true));
        assertThat(saIt.next(), is(3));
        assertThat(saIt.hasNext(), is(false));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenTryToAddMoreElementsTheArrayCapacityThenException() {
        sa.add(4);
    }
}
