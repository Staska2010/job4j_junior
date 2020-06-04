package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.List;
import java.util.function.Predicate;

public class DefaultReport implements Reportable {
    @Override
    public String generate(List<Employee> list) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : list) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired().toString()).append(";")
                    .append(employee.getFired().toString()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
