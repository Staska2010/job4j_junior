package ru.job4j.design.lsp;

import ru.job4j.design.lsp.store.Store;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MockControlQuality extends ControlQuality {
    public MockControlQuality(Store shop, Store wareHouse, Store trash) {
        super(shop, wareHouse, trash);
    }

    @Override
    public long checkExpiredDate(Food food) {
        LocalDate now = LocalDate.of(2020, 1, 1);
        LocalDate expiredDate = food.getExpiredDate();
        LocalDate productionDate = food.getProductionDate();
        long passedDaysCount = ChronoUnit.DAYS.between(productionDate, now);
        long freshPeriodDaysCount = ChronoUnit.DAYS.between(productionDate, expiredDate);
        return passedDaysCount * 100 / freshPeriodDaysCount;
    }
}
