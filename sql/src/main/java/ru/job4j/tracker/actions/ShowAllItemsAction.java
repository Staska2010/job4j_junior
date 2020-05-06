package ru.job4j.tracker.actions;

import ru.job4j.tracker.*;

import java.util.function.Consumer;

public class ShowAllItemsAction implements UserAction {

    @Override
    public int key() {
        return 1;
    }

    @Override
    public String name() {
        return  this.key() + ". " + "Вывод заявок";
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        for (Item iterator : tracker.findAll()) {
            output.accept("ID: " + iterator.getId() + "; name: " + iterator.getName() + "; desc: "
                    + iterator.getDesc());
        }
        return true;
    }
}
