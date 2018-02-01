package com.invoice_components.panel;

import com.invoice_components.table_model.ReportTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.table_model.ReportTableModel.REPORT_TABLE_MODEL_BEAN;

public class ReportTablePanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private JTable reportTable;
    private ReportTableModel reportTableModel;

    public ReportTablePanel(ApplicationContext app) {
        setLayout(flowLayout);
        reportTableModel = (ReportTableModel) app.getBean(REPORT_TABLE_MODEL_BEAN);
        reportTable = new JTable(reportTableModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(reportTable);
        add(jScrollPane);
    }
}
