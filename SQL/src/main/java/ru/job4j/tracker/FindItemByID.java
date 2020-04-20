package ru.job4j.tracker;

import java.util.function.Consumer;

public class FindItemByID extends BaseAction {
    public FindItemByID(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String id = input.askStr("Введите ID заявки :");
        Item item = tracker.findById(id);
        if (item != null) {
            output.accept("Заявка: ID" + item.getId() + ", name:  " + item.getName()
                    + "; desc: " + item.getDesc());
        } else {
            output.accept("Заявка c ID: " + id + " не найдена");
        }
        return true;
    }
}
