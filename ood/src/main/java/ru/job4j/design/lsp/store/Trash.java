package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.LinkedList;
import java.util.List;

public class Trash extends Store {
    List<Food> trash = new LinkedList<>();

    @Override
    public void addToStore(Food food) {
        trash.add(food);
    }

    @Override
    public List<Food> showStoreContents() {
        return trash;
    }
}
