package ru.job4j.tracker;

import ru.job4j.tracker.actions.ShowAllItemsAction;

import java.util.function.Consumer;

public class DeleteItem extends BaseAction {
    public DeleteItem(int key, String name) {
        super(key, name);
    }
    @Override
    public boolean execute(Input input, Store tracker, Consumer<String> output) {
        String id = input.askStr("Введите ID заявки :");
        if (tracker.delete(id)) {
            output.accept("Заявка с ID: " + id + " удалена");
            new ShowAllItemsAction().execute(input, tracker, System.out::println);
        } else {
            System.out.println("Заявка c ID: " + id + " не найдена");
        }
        return true;
    }
}
