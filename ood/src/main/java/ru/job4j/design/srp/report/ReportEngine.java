package ru.job4j.design.srp.report;

import ru.job4j.design.srp.DataSupplier;
import ru.job4j.design.srp.Store;

/**
 * Basic interface for different reports
 */
public class ReportEngine {
    Reportable report;
    Store store;
    DataSupplier ds;

    public ReportEngine(Store store) {
        report = new DefaultReport();
        this.store = store;
        ds = new DataSupplier(store);
    }

    public String generateReport() {
        return report.generate(ds.getData(em -> true));
    }

    public void setReportType(Reportable reportType) {
        report = reportType;
    }
}
