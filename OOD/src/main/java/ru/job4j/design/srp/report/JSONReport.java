package ru.job4j.design.srp.report;

import com.google.gson.Gson;
import ru.job4j.design.srp.Employee;

import java.util.List;

public class JSONReport implements Reportable {
    @Override
    public String generate(List<Employee> list) {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        for (Employee emp : list) {
            sb.append(gson.toJson(emp));
        }
        return sb.toString();
    }
}
