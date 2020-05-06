package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

import java.util.List;
import java.util.function.Consumer;

public class FinditemsByNameAction implements UserAction {
    @Override
    public int key() {
        return 5;
    }

    @Override
    public String name() {
        return this.key() + ". " + "Найти заявки по названию";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
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
