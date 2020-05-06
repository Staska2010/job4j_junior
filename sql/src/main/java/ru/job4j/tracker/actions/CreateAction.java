package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

import java.util.function.Consumer;

public class CreateAction implements UserAction {
    @Override
    public int key() {
        return 0;
    }

    @Override
    public String name() {
        return this.key() + ". " + "Добавление новой заявки";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
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
