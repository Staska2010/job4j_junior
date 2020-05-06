package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;

public class TrackerSingletonEager {
    private static final TrackerSingletonEager INSTANCE = new TrackerSingletonEager();
    private MemTracker memTracker = new MemTracker();

    private TrackerSingletonEager() {
    }

    public static TrackerSingletonEager getInstance() {
        return INSTANCE;
    }

    public Item createItem(Item item) {
        return memTracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
