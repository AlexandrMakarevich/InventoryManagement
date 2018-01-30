package com.invoice_components.panel;

import com.invoice_components.table.ReportTable;
import com.invoice_components.table.ReportTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.table.ReportTable.REPORT_TABLE_BEAN;
import static com.invoice_components.table.ReportTableModel.REPORT_TABLE_MODEL_BEAN;

public class ReportTablePanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private ReportTable reportTable;
    private ReportTableModel reportTableModel;

    public ReportTablePanel(ApplicationContext app) {
        setLayout(flowLayout);
        reportTable = (ReportTable) app.getBean(REPORT_TABLE_BEAN);
        reportTableModel = (ReportTableModel) app.getBean(REPORT_TABLE_MODEL_BEAN);
        reportTable.setModel(reportTableModel);
        reportTable.refreshTable();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(reportTable);
        add(jScrollPane);
    }

    public ReportTable getReportTable() {
        return reportTable;
    }
}
