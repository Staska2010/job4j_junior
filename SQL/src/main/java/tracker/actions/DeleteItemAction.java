package ru.job4j.tracker.actions;

import ru.job4j.tracker.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.tracker.UserAction;

import java.util.function.Consumer;

public class DeleteItemAction implements UserAction {
    @Override
    public int key() {
        return 3;
    }

    @Override
    public String name() {
        return this.key() + ". " + "Удаление заявки";
    }

    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String id = input.askStr("Введите ID заявки :");
        if (tracker.delete(id)) {
            output.accept("Заявка с ID: " + id + " удалена");
            new ShowAllItemsAction().execute(input, tracker, System.out::println);
        } else {
            output.accept("Заявка c ID: " + id + " не найдена");
        }
        return true;
    }
}
