package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

import java.util.function.Consumer;

public class FindByIdAction implements UserAction {
    @Override
    public int key() {
        return 4;
    }

    @Override
    public String name() {
        return this.key() + ". " + "Найти заявку по ID";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
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
