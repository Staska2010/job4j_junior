package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MemTracker {
    private final List<Item> items = new ArrayList<>();

    public Item add(Item item) {
        item.setId(this.generateId());
        items.add(item);
        return item;
    }

    private String generateId() {
        Random rnd = new Random();
        return String.valueOf(rnd.nextLong() + System.currentTimeMillis());
    }

    public List<Item> findAll() {
        return items;
    }

    public boolean replace(String id, Item item) {
        boolean result = false;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.get(i).setName(item.getName());
                items.get(i).setDesc(item.getDesc());
                result = true;
            }
        }
        return result;
    }

    public boolean delete(String id) {
        boolean result = false;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                result = true;
                break;
            }
        }
        return result;
    }

    public Item findById(String id) {
        Item result = null;
        for (Item item : items) {
            if (item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }

    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (Item item : items) {
            if (item.getName().equals(name)) {
                result.add(item);
            }
        }
        return result;
    }
}
