package com.invoice_components.table_model;

import com.entity.InvoiceItem;
import org.springframework.stereotype.Component;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static com.invoice_components.table_model.InvoiceItemTableModel.INVOICE_ITEM_TABLE_MODEL_BEAN;

@Component(INVOICE_ITEM_TABLE_MODEL_BEAN)
public class InvoiceItemTableModel extends AbstractTableModel {

    private List<String> columnName = Arrays.asList("Product", "Quantity");
    private List<InvoiceItem> invoiceItems = new ArrayList<>();
    public static final String INVOICE_ITEM_TABLE_MODEL_BEAN = "invoiceItemTableModel";

    @Override
    public int getRowCount() {
        return invoiceItems.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem invoiceItem = invoiceItems.get(rowIndex);
        if (columnIndex == 0) {
            return invoiceItem.getProduct().getProductName();
        }
        if (columnIndex == 1) {
            return invoiceItem.getProductQuantity();
        }
        throw new IllegalArgumentException("Wrong column index" + columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        if(column == 0) {
            return columnName.get(0);
        }
        if(column == 1) {
            return columnName.get(1);
        }
        throw new IllegalArgumentException("Wrong column index " + column);
    }

    public void resetModel() {
        invoiceItems = new ArrayList<>();
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public List<InvoiceItem> getInvoiceItems() {
        return invoiceItems;
    }
}
