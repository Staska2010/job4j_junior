package ru.job4j.design.srp.report;

public class ReportFactory {
    public ReportEngine create(ReportType typeOfReport) {
        ReportEngine report = null;
        switch (typeOfReport) {
            case AccountsReportEngine:
                report = new AccountsReportEngine();
                break;
            case ITReportEngine:
                report = new ITReportEngine();
                break;
            case HRReportEngine:
                report = new HRReportEngine();
                break;
            default:
                report = new DefaultReportEngine();
        }
        return report;
    }
}
