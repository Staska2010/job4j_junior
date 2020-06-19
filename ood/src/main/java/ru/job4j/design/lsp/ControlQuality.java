package ru.job4j.design.lsp;

import ru.job4j.design.lsp.store.Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Класс, контролирующий размещение продуктов по различным хранилищам,
 * в зависимости от их срока годности.
 * Если срок годности израсходован меньше чем на 25% продукт направляется в Warehouse.
 * Если срок годности от 25% до 75%, направляется в Shop
 * Если срок годности больше 75% то добавляется скидка на продукт, отправляется в Shop
 * Если срок годности вышел - в Trash.
 */
public class ControlQuality {
    private FoodRelocateStrategy relocateFood;
    private final Store shop;
    private final Store wareHouse;
    private final Store trash;
    private final LocalDate now = LocalDate.now();

    public ControlQuality(Store shop, Store wareHouse, Store trash) {
        this.shop = shop;
        this.wareHouse = wareHouse;
        this.trash = trash;
    }

    /**
     * Перемещение продукта в хранилище по алгоритму, который выбирается "на лету"
     * в зависимости от срока годности.
     *
     * @param food
     */
    void relocateTheFood(Food food) {
        long expiredRatio = checkExpiredDate(food);
        chooseRelocateStrategy(expiredRatio);
        relocateFood.relocate(food);
    }

    /**
     * Вычисление значения отношения даты окончания срока годности к дате выпуска в текущий момент
     *
     * @param food - продукт, для которого вычисляется значение
     * @return - процентное соотношение оставшегося "срока годности" к прошедшему "сроку годности"
     */
    long checkExpiredDate(Food food) {
        LocalDate expiredDate = food.getExpiredDate();
        LocalDate productionDate = food.getProductionDate();
        long remainingDaysCount = ChronoUnit.DAYS.between(expiredDate, now);
        long freshPeriodDaysCount = ChronoUnit.DAYS.between(productionDate, expiredDate);
        return remainingDaysCount * 100 / freshPeriodDaysCount;
    }

    /**
     * Выбор "алгоритма" обработки товара в зависимости от соотношения оставшегося срока годности к прошедшему
     * "Алгоритм" - потому что в данном контексте задачи происходит только выбор хранилища без
     * какой-либо сложной обработки. Выражается в изменении лямбды.
     *
     * @param expiredRatio
     */
    private void chooseRelocateStrategy(long expiredRatio) {
        if (expiredRatio >= 0 && expiredRatio < 25) {
            relocateFood = wareHouse::addToStore;
        }
        if (expiredRatio > 25 && expiredRatio < 75) {
            relocateFood = shop::addToStore;
        }
        if (expiredRatio > 75 && expiredRatio < 100) {
            relocateFood = food -> {
                food.setDiscount(50);
                shop.addToStore(food);
            };
        }
        if (expiredRatio >= 100 || expiredRatio < 0) {
            relocateFood = trash::addToStore;
        }
    }
}
