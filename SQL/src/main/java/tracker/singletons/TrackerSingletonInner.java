package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class TrackerSingletonInner {
    private Tracker tracker = new Tracker();

    private TrackerSingletonInner() {
    }

    public static TrackerSingletonInner getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingletonInner INSTANCE = new TrackerSingletonInner();
    }

    public Item createItem(Item item) {
        return tracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
