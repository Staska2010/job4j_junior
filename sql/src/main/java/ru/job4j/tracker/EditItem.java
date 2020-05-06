package ru.job4j.tracker;

import java.util.function.Consumer;

public class EditItem extends BaseAction {
    public EditItem(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String id = input.askStr("Введите ID редактируемой заявки :");
        String name = input.askStr("Введите новое название заявки :");
        String desc = input.askStr("Введите новое описание :");
        Item item = new Item(name, desc);
        if (tracker.replace(id, item)) {
            output.accept("Заявка успешна изменена");
        } else {
            output.accept("Заявка с ID:" + item.getName() + " не найдена");
        }
        return true;
    }
}
