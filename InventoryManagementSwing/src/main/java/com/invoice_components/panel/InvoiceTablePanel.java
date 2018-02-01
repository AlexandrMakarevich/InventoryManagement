package com.invoice_components.panel;

import com.invoice_components.table_model.InvoiceTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.table_model.InvoiceTableModel.INVOICE_TABLE_MODEL_BEAN;

public class InvoiceTablePanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private JTable invoiceTable;
    private InvoiceTableModel invoiceTableModel;

    public InvoiceTablePanel(ApplicationContext app ){
        setLayout(flowLayout);
        invoiceTableModel = (InvoiceTableModel) app.getBean(INVOICE_TABLE_MODEL_BEAN);
        invoiceTable = new JTable(invoiceTableModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(invoiceTable);
        add(jScrollPane);
    }

    public InvoiceTableModel getInvoiceTableModel() {
        return invoiceTableModel;
    }

    public JTable getInvoiceTable() {
        return invoiceTable;
    }
}
