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
    private MockControlQuality cq;
    private Shop shop;
    private WareHouse wareHouse;
    private Trash trash;

    @Before
    public void init() {
        shop = new Shop();
        wareHouse = new WareHouse();
        trash = new Trash();
        cq = new MockControlQuality(shop, wareHouse, trash);
    }

    @Test
    public void whenAddFoodWithDifferentShelfLifeTheyAppearsInAppropriateStores() {
        Food milk = new Food("Milk",
                LocalDate.of(2020, 5, 11),
                LocalDate.of(2019, 1, 12),
                50);
        Food milkFromFuture = new Food("Milk From The Future",
                LocalDate.of(2020, 12, 31),
                LocalDate.of(2020, 1, 10),
                50);
        Food whiteBread = new Food("White Bread",
                LocalDate.of(2020, 1, 3),
                LocalDate.of(2020, 1, 1),
                50);
        Food wheatFlour = new Food("Wheat Flour",
                LocalDate.of(2020, 1, 5),
                LocalDate.of(2019, 11, 10),
                50);
        cq.relocateTheFood(milk);
        cq.relocateTheFood(milkFromFuture);
        cq.relocateTheFood(whiteBread);
        cq.relocateTheFood(wheatFlour);
        assertTrue(shop.showStoreContents().contains(milk));
        assertTrue(trash.showStoreContents().contains(milkFromFuture)); //product with illegal production date
        assertTrue(wareHouse.showStoreContents().contains(whiteBread));
        assertTrue(shop.showStoreContents().contains(wheatFlour));
        int index = shop.showStoreContents().indexOf(wheatFlour);
        assertThat(shop.showStoreContents().get(index).getDiscount(), is(50)); //discount was set to 50%


    }
}
