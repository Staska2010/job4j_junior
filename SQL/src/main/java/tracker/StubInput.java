package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class StubInput implements Input {
    private final List<String> answers;
    private int position;

    public StubInput(List<String> answers) {
        this.answers = answers;
    }

    @Override
    public String askStr(String input) {
        return answers.get(position++);
    }

    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    @Override
    public int askInt(String question, int max) {
        int select = askInt(question);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return select;
    }
}
