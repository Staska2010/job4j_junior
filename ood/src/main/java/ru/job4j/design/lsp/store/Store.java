package ru.job4j.design.lsp.store;

import ru.job4j.design.lsp.Food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public interface Store {
    boolean isValid(Food food);

    void addToStore(Food food);

    /**
     * Дефолтный метод:
     * Вычисление значения отношения даты окончания срока годности к дате выпуска в текущий момент
     * Реализация метода одинакова для всех имплементаций интерфейса.
     *
     * @param food - продукт, для которого вычисляется значение
     * @return - процентное соотношение оставшегося "срока годности" к прошедшему "сроку годности"
     */
    default long checkExpiredDate(Food food) {
        LocalDate now = LocalDate.now();
        LocalDate expiredDate = food.getExpiredDate();
        LocalDate productionDate = food.getProductionDate();
        long remainingDaysCount = ChronoUnit.DAYS.between(now, expiredDate);
        long freshPeriodDaysCount = ChronoUnit.DAYS.between(productionDate, expiredDate);
        return remainingDaysCount * 100 / freshPeriodDaysCount;
    }
}