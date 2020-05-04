package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;

public enum TrackerSingletonEnum {
    INSTANCE;
    private MemTracker memTracker = new MemTracker();

    Item createItem(Item item) {
        return memTracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
