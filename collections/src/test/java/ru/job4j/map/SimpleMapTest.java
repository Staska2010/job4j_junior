package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.stream.IntStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleMapTest {
    @Test
    public void whenCreateMapWithIntegerKeyThenPutWorksCorrectly() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        assertThat(sm.insert(10, "First"), is(true));
        assertThat(sm.insert(20, "First"), is(true));
    }

    @Test
    public void whenCreateMapWithStringKeyThenPutWorksCorrectly() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertThat(sm.insert("10", "First"), is(true));
        assertThat(sm.insert("20", "First"), is(true));
        assertThat(sm.insert(null, "Second"), is(false));
    }


    @Test
    public void whenPutPairThatExistInMapThenShouldOverWrite() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertThat(sm.insert("10", "First"), is(true));
        assertThat(sm.insert("10", "Second"), is(true));
        assertThat(sm.get("10"), is("Second"));
    }

    @Test
    public void whenPutPairThenGetByKey() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        sm.insert("10", "First");
        assertThat(sm.get("10"), is("First"));
    }

    @Test
    public void whenGetPairNotExistingInMapThenFalse() {
        SimpleMap<String, String> sm = new SimpleMap<>();
        assertNull(sm.get("10"));
    }

    @Test
    public void whenNumberOfBinsIsLargeThenResizeMap() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        assertThat(sm.getSize(), is(16));
        IntStream.range(0, 14).forEach(n -> sm.insert(n, "Value" + n));
        assertThat(sm.getSize(), is(32));
    }

    @Test
    public void whenResizedMapThanPairsAreAvailable() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        IntStream.range(0, 14).forEach(n -> sm.insert(n, "Value" + n));
        assertThat(sm.get(2), is("Value2"));
    }

    @Test
    public void whenRemovePairThatExistInMapThenTrue() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.insert(0, "First");
        assertThat(sm.delete(0), is(true));
    }

    @Test
    public void whenRemovePairThatNotExistInMapThenFalse() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.insert(0, "First");
        assertThat(sm.delete(1), is(false));
    }


    @Test
    public void whenUseIteratorItShouldWorkProperly() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.insert(0, "First");
        sm.insert(1, "First");
        sm.insert(2, "First");
        sm.insert(3, "First");
        Iterator it = sm.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertNotNull(it.next());
        assertThat(it.hasNext(), is(true));
        assertNotNull(it.next());
        assertThat(it.hasNext(), is(true));
        assertNotNull(it.next());
        assertThat(it.hasNext(), is(true));
        assertNotNull(it.next());
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whileUseIteratorShouldThrowExceptionIfMapWasModified() {
        SimpleMap<Integer, String> sm = new SimpleMap<>();
        sm.insert(0, "First");
        sm.insert(1, "First");
        Iterator it = sm.iterator();
        it.next();
        sm.insert(2, "First");
        it.next();
    }
}
