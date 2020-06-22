package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.LinkedList;
import java.util.List;

public class WareHouse implements Store {
    List<Food> wareHouseStore = new LinkedList<>();

    @Override
    public boolean isValid(Food food) {
        long expiredRatio = checkExpiredDate(food);
        return expiredRatio >= 0 && expiredRatio < 25 || expiredRatio == 100; // expiredRatio == 100 - граничный случай
    }

    @Override
    public void addToStore(Food food) {
        wareHouseStore.add(food);
    }
}
