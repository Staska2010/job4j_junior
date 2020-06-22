package ru.job4j.design.lsp;

import ru.job4j.design.lsp.store.Store;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс, контролирующий размещение продуктов по различным хранилищам,
 * в зависимости от их срока годности.
 * Если срок годности израсходован меньше чем на 25% продукт направляется в Warehouse.
 * Если срок годности от 25% до 75%, направляется в Shop
 * Если срок годности больше 75% то добавляется скидка на продукт, отправляется в Shop
 * Если срок годности вышел - в Trash.
 */
public class ControlQuality {
    private List<Store> stores = new LinkedList<>();

    public ControlQuality(Store shop, Store wareHouse, Store trash) {
        stores.add(shop);
        stores.add(wareHouse);
        stores.add(trash);
    }

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    /**
     * Перемещение продукта в подходящее хранилище.
     * Условия перемещения определяются самим хранилищем.
     *
     * @param food - проверяемый и перемещаемы продукт.
     */
    void checkTheFood(Food food) {
        for (Store nextStore : stores) {
            if (nextStore.isValid(food)) {
                nextStore.addToStore(food);
                break;
            }
        }
    }
}
