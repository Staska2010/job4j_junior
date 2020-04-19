package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FindItemsByName extends BaseAction {
    public FindItemsByName(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String name = input.askStr("Введите имя заявки :");
        List<Item> items = tracker.findByName(name);
        if (items.size() == 0) {
            output.accept("Заявки с именем: " + name + " не найдены");
        } else {
            output.accept("Заявки:");
            for (Item iterator : items) {
                output.accept("ID: " + iterator.getId() + "; name: " + iterator.getName() + "; desc: "
                        + iterator.getDesc());
            }
        }
        return true;
    }
}
