package ru.job4j.design.lsp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.store.Shop;
import ru.job4j.design.lsp.store.Trash;
import ru.job4j.design.lsp.store.WareHouse;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ControlQualityTest {
    private ControlQuality cq;
    private Shop shop;
    private WareHouse wareHouse;
    private Trash trash;

    @Before
    public void init() {
        shop = new Shop();
        wareHouse = new WareHouse();
        trash = new Trash();
        cq = new ControlQuality(shop, wareHouse, trash);
    }

    @Test
    public void whenAddFoodWithDifferentShelfLifeTheyAppearsInAppropriateStores() {
        Food milk = new Food("Milk",
                LocalDate.now().plusDays(7),
                LocalDate.now().minusDays(7),
                50);
        Food milkFromFuture = new Food("Milk From The Future",
                LocalDate.now().plusDays(10),
                LocalDate.now().plusDays(2),
                50);
        Food whiteBread = new Food("White Bread",
                LocalDate.now().plusDays(30),
                LocalDate.now(),
                50);
        Food wheatFlour = new Food("Wheat Flour",
                LocalDate.now().plusDays(45),
                LocalDate.now().minusDays(12),
                50);
        cq.checkTheFood(milk);
        cq.checkTheFood(milkFromFuture);
        cq.checkTheFood(whiteBread);
        cq.checkTheFood(wheatFlour);
        assertTrue(shop.isValid(milk));
        assertTrue(trash.isValid(milkFromFuture));
        assertTrue(wareHouse.isValid(whiteBread));
        assertTrue(shop.isValid(wheatFlour));
        assertFalse(trash.isValid(milk));
        assertFalse(wareHouse.isValid(wheatFlour));
        int index = shop.getContents().indexOf(wheatFlour);
        assertThat(shop.getContents().get(index).getDiscount(), is(50)); //discount was set to 50%


    }
}
