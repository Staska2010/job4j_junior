package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;

import java.util.List;

public interface Reportable {
    String generate(List<Employee> list);
}
