package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public enum TrackerSingletonEnum {
    INSTANCE;
    private Tracker tracker = new Tracker();

    Item createItem(Item item) {
        return tracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
