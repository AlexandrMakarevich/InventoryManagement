package com.invoice_components.panel;

import com.invoice_components.table.InvoiceTable;
import com.invoice_components.table.InvoiceTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.table.InvoiceTable.INVOICE_TABLE_BEAN;
import static com.invoice_components.table.InvoiceTableModel.INVOICE_TABLE_MODEL_BEAN;

public class InvoiceTablePanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private InvoiceTable invoiceTable;
    private InvoiceTableModel invoiceTableModel;

    public InvoiceTablePanel(ApplicationContext app ){
        setLayout(flowLayout);
        invoiceTable = (InvoiceTable) app.getBean(INVOICE_TABLE_BEAN);
        invoiceTableModel = (InvoiceTableModel) app.getBean(INVOICE_TABLE_MODEL_BEAN);
        invoiceTable.setModel(invoiceTableModel);
        invoiceTable.refreshModel();
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(invoiceTable);
        add(jScrollPane);
    }

    public InvoiceTable getInvoiceTable() {
        return invoiceTable;
    }
}
