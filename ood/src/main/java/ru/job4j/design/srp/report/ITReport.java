package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.List;
import java.util.function.Predicate;

public class ITReport implements Reportable {
    @Override
    public String generate(List<Employee> list) {
        Predicate<Employee> filter = em -> true;
        StringBuilder sb = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<table>");
        for (Employee employee : list) {
            sb.append("<tr>")
                    .append("<td>")
                    .append(employee.getName())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getHired().toString())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getFired().toString())
                    .append("</td>")
                    .append("<td>")
                    .append(employee.getSalary())
                    .append("</td>")
                    .append("</tr>");
        }
        sb.append("</table>")
                .append("</html>");
        return sb.toString();
    }
}
