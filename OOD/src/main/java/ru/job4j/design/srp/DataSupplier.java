package ru.job4j.design.srp;

import java.io.DataInputStream;
import java.util.List;
import java.util.function.Predicate;

public class DataSupplier {
    Store store;
    public DataSupplier(Store store) {
        this.store = store;
    }
    public List<Employee> getData(Predicate<Employee> filter) {
        return store.findBy(filter);
    }
}
