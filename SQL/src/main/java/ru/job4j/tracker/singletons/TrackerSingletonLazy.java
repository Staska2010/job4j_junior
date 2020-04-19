package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class TrackerSingletonLazy {
    private static TrackerSingletonLazy instance;
    private Tracker tracker = new Tracker();

    private TrackerSingletonLazy() {
    }

    public static TrackerSingletonLazy getInstance() {
        if (instance == null) {
            instance = new TrackerSingletonLazy();
        }
        return instance;
    }

    public Item createItem(Item item) {
        return tracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
