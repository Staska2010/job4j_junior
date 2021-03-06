package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class HRReport implements Reportable {
    @Override
    public String generate(List<Employee> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Name; Salary;")
                .append(System.lineSeparator());
        list = list.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .collect(Collectors.toList());
        for (Employee employee : list) {
            sb.append(employee.getName()).append(";")
                    .append(String.format("%.2fруб", employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return sb.toString();
    }
}
