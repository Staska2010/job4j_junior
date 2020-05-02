package ru.job4j.tracker;

import java.util.function.Consumer;

public interface UserAction {
    int key();

    String name();

    boolean execute(Input input, Tracker tracker, Consumer<String> output);
}
