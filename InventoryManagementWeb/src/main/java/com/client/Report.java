package com.client;

public class Report {

    private String reportFormat;

    public Report(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public Report() {
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }
}
