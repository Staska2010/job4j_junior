package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.LinkedList;
import java.util.List;

public class Shop extends Store {
    List<Food> shopStore = new LinkedList<>();

    @Override
    public void addToStore(Food food) {
        shopStore.add(food);
    }

    @Override
    public List<Food> showStoreContents() {
        return shopStore;
    }
}
