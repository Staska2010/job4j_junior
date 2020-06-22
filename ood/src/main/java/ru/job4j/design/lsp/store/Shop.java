package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.LinkedList;
import java.util.List;

public class Shop implements Store {
    List<Food> shopStore = new LinkedList<>();

    @Override
    public boolean isValid(Food food) {
        long expiredRatio = checkExpiredDate(food);
        return expiredRatio > 25 && expiredRatio < 100;
    }

    @Override
    public void addToStore(Food food) {
        long expiredRatio = checkExpiredDate(food);
        if (expiredRatio > 75 && expiredRatio < 100) {
            food.setDiscount(50);
        }
        shopStore.add(food);
    }

    public List<Food> getContents() {
        return shopStore;
    }
}
