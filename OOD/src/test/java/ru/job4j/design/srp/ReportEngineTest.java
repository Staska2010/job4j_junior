package ru.job4j.design.srp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.srp.report.ReportEngine;
import ru.job4j.design.srp.report.ReportFactory;
import ru.job4j.design.srp.report.ReportType;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

public class ReportEngineTest {
    MemStore store;
    Employee worker;

    @Before
    public void init() {
        store = new MemStore();
        LocalDate now = LocalDate.now();
        worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
    }

    @Test
    public void whenDefaultReportGenerated() {
        ReportEngine engine = new ReportFactory().create(ReportType.DefaultReportEngine);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(worker.getSalary()).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(store), is(expect.toString()));
    }

    @Test
    public void whenITReportGenerated() {
        ReportEngine engine = new ReportFactory().create(ReportType.ITReportEngine);
        StringBuilder expect = new StringBuilder()
                .append("<!DOCTYPE html>")
                .append("<html>")
                .append("<table>")
                .append("<tr>")
                .append("<td>")
                .append(worker.getName())
                .append("</td>")
                .append("<td>")
                .append(worker.getHired())
                .append("</td>")
                .append("<td>")
                .append(worker.getFired())
                .append("</td>")
                .append("<td>")
                .append(worker.getSalary())
                .append("</td>")
                .append("</tr>")
                .append("</table>")
                .append("</html>");
        System.out.println(expect);
        System.out.println(engine.generate(store));
        assertThat(engine.generate(store), is(expect.toString()));
    }

    @Test
    public void whenAccountantsReportGenerated() {
        ReportEngine engine = new ReportFactory().create(ReportType.AccountsReportEngine);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(String.format("%.2fруб", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(store), is(expect.toString()));
    };

    @Test
    public void whenHRReportGenerated() {
        ReportEngine engine = new ReportFactory().create(ReportType.HRReportEngine);
        Employee worker2 = new Employee("Igor", LocalDate.now(), LocalDate.now(), 200);
        store.add(worker2);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(String.format("%.2fруб", worker2.getSalary())).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(String.format("%.2fруб", worker.getSalary())).append(";")
                .append(System.lineSeparator());
        assertThat(engine.generate(store), is(expect.toString()));
    }
}
