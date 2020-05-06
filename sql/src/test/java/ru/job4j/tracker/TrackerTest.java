package ru.job4j.tracker;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class TrackerTest {

    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void whenAddNewItemToDBThenSizeIsOne() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            tracker.add(new Item("name", "desc"));
            assertThat(tracker.findByName("name").size(), is(1));
        }
    }

    @Test
    public void whenReplaceItemInRightWayThenTrue() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item firstItem = tracker.add(new Item("name", "desc"));
            Item secondItem = new Item("name2", "desc2");
            String firstItemId = firstItem.getId();
            assertTrue(tracker.replace(firstItemId, secondItem));
        }
    }

    @Test
    public void whenDeleteExistingItemThenTrue() throws Exception {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollBack.create(this.init()))) {
            Item item = tracker.add(new Item("name", "desc"));
            assertTrue(tracker.delete(item.getId()));
        }
    }
}
