package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;

public class ITReportEngine implements ReportEngine {
    @Override
    public String generate(Store store) {
        Predicate<Employee> filter = em -> true;
        StringBuilder sb = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<table>");
        for (Employee employee : store.findBy(filter)) {
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
