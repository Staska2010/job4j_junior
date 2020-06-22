package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.util.LinkedList;
import java.util.List;

public class Trash implements Store {
    List<Food> trash = new LinkedList<>();

    @Override
    public boolean isValid(Food food) {
        long expiredRatio = checkExpiredDate(food);
        return expiredRatio >= 100 || expiredRatio < 0;
    }

    @Override
    public void addToStore(Food food) {
        trash.add(food);
    }
}
