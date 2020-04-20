package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

import java.util.function.Consumer;

public class StubAction implements UserAction {
    public boolean call = false;

    @Override
    public int key() {
        return 0;
    }

    @Override
    public String name() {
        return "StubAction";
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        call = true;
        return false;
    }
}
