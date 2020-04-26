package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;

public class TrackerSingletonLazy {
    private static TrackerSingletonLazy instance;
    private MemTracker memTracker = new MemTracker();

    private TrackerSingletonLazy() {
    }

    public static TrackerSingletonLazy getInstance() {
        if (instance == null) {
            instance = new TrackerSingletonLazy();
        }
        return instance;
    }

    public Item createItem(Item item) {
        return memTracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
