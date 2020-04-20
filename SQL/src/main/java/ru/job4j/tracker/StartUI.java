package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class StartUI {
    final Consumer<String> output;

    StartUI(Consumer<String> output) {
        this.output = output;
    }

    public void init(Input input, Tracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Введите пункт меню : ", 7);
            UserAction action = actions.get(select);
            run = action.execute(input, tracker, System.out::println);
        }
    }

    private void showMenu(List<UserAction> actions) {
        output.accept("Меню.");
        for (UserAction i : actions) {
            output.accept(i.name());
        }
    }

    /**
     * Запуск программы.
     *
     * @param args
     */
    public static void main(String[] args) {
        List<UserAction> actions = new ArrayList<>(Arrays.asList(new CreateAction(),
                new ShowAllItemsAction(), new DeleteItemAction(), new EditItemAction(),
                new FindByIdAction(), new FinditemsByNameAction(), new ExitAction()));
        Tracker tracker = new Tracker();
        Input input = new ValidateInput(new ConsoleInput());
        new StartUI(System.out::println).init(input, tracker, actions);
    }
}
