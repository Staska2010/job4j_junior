package ru.job4j.tracker;

import java.util.function.Consumer;

public class ExitTracker extends BaseAction {
    public ExitTracker(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        return false;
    }
}
