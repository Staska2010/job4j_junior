package ru.job4j.design.lsp;

import java.time.LocalDate;

public class Food {
    private String name;
    private final LocalDate expiredDate;
    private final LocalDate productionDate;
    private int price;
    private int discount;

    public Food(String name, LocalDate expiredDate, LocalDate productionDate, int price) {
        this.name = name;
        this.expiredDate = expiredDate;
        this.productionDate = productionDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public LocalDate getProductionDate() {
        return productionDate;
    }

    public int getPrice() {
        return price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
