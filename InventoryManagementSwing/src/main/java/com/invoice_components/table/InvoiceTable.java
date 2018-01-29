package com.invoice_components.table;

import com.entity.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import static com.invoice_components.table.InvoiceTable.INVOICE_TABLE_BEAN;

@Component(INVOICE_TABLE_BEAN)
public class InvoiceTable extends JTable {

    public static final String INVOICE_TABLE_BEAN = "invoiceTable";
    private InvoiceTableModel invoiceTableModel;
    private List<InvoiceItem> invoiceItems = new ArrayList<>();

    public void refreshModel() {
        invoiceTableModel.setInvoiceItems(invoiceItems);
        invoiceTableModel.fireTableDataChanged();
    }

    @Autowired
    public void setInvoiceTableModel(InvoiceTableModel invoiceTableModel) {
        this.invoiceTableModel = invoiceTableModel;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public InvoiceTableModel getInvoiceTableModel() {
        return invoiceTableModel;
    }
}
