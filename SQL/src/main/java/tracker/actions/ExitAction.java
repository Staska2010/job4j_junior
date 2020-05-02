package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

import java.util.function.Consumer;

public class ExitAction implements UserAction {
    @Override
    public int key() {
        return 6;
    }

    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        return false;
    }
}
