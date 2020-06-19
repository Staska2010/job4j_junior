package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.LinkedList;
import java.util.List;

public class WareHouse extends Store {
    List<Food> wareHouseStore = new LinkedList<>();

    @Override
    public void addToStore(Food food) {
        wareHouseStore.add(food);
    }

    @Override
    public List<Food> showStoreContents() {
        return wareHouseStore;
    }
}
