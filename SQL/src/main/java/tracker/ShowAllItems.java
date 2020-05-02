package ru.job4j.tracker;

import java.util.function.Consumer;

public class ShowAllItems extends BaseAction {
    public ShowAllItems(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        for (Item iterator : tracker.findAll()) {
            output.accept("ID: " + iterator.getId() + "; name: " + iterator.getName() + "; desc: "
                    + iterator.getDesc());
        }
        return true;
    }
}
