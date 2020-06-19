package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.List;

public abstract class Store {
    public abstract void addToStore(Food food);
    public abstract List<Food> showStoreContents();
}