package com.invoice_components.panel;

import com.invoice_components.table_model.InvoiceItemTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.table_model.InvoiceItemTableModel.INVOICE_ITEM_TABLE_MODEL_BEAN;

public class InvoiceTablePanel extends JPanel {

    private FlowLayout flowLayout = new FlowLayout();
    private JTable invoiceTable;
    private InvoiceItemTableModel invoiceItemTableModel;

    public InvoiceTablePanel(ApplicationContext app ){
        setLayout(flowLayout);
        invoiceItemTableModel = (InvoiceItemTableModel) app.getBean(INVOICE_ITEM_TABLE_MODEL_BEAN);
        invoiceTable = new JTable(invoiceItemTableModel);
        JScrollPane jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(invoiceTable);
        add(jScrollPane);
    }

    public InvoiceItemTableModel getInvoiceItemTableModel() {
        return invoiceItemTableModel;
    }

    public JTable getInvoiceTable() {
        return invoiceTable;
    }
}
