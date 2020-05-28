package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Store;

public interface ReportEngine {
    String generate(Store store);
}
