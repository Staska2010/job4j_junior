package ru.job4j.tracker;

import java.util.function.Consumer;

public class CreateItem extends BaseAction {

    public CreateItem(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String name = input.askStr("Введите имя заявки :");
        String desc = input.askStr("Введите описание заявки :");
        Item item = new Item(name, desc);
        if (tracker.add(item) != null) {
            output.accept(" Новая заявка с getId: " + item.getId());
        } else {
            output.accept("База переполнена!");
        }
        return true;
    }
}
