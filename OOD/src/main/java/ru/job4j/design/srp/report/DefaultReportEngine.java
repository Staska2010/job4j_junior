package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;

public class DefaultReportEngine implements ReportEngine {
    @Override
    public String generate(Store store) {
        Predicate<Employee> filter = em -> true;
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        text.append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(";")
                    .append(employee.getHired().toString()).append(";")
                    .append(employee.getFired().toString()).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
