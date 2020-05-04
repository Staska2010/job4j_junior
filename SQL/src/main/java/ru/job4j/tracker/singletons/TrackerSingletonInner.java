package ru.job4j.tracker.singletons;

import ru.job4j.tracker.Item;
import ru.job4j.tracker.MemTracker;

public class TrackerSingletonInner {
    private MemTracker memTracker = new MemTracker();

    private TrackerSingletonInner() {
    }

    public static TrackerSingletonInner getInstance() {
        return Holder.INSTANCE;
    }

    private static final class Holder {
        private static final TrackerSingletonInner INSTANCE = new TrackerSingletonInner();
    }

    public Item createItem(Item item) {
        return memTracker.add(item);
    }
    /*****
     *  and other Tracker's methods
     */
}
