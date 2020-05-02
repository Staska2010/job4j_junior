package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class TrackerSingletonEager {
    private static final TrackerSingletonEager INSTANCE = new TrackerSingletonEager();
    private Tracker tracker = new Tracker();

    private TrackerSingletonEager() {
    }

    public static TrackerSingletonEager getInstance() {
        return INSTANCE;
    }

    public Item createItem(Item item) {
        return tracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
