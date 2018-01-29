package com.invoice_components.listener;

import com.entity.InvoiceItem;
import com.invoice_components.table.InvoiceTable;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import static com.invoice_components.listener.DeleteButtonListener.DELETE_BUTTON_LISTENER_BEAN;

@Component(DELETE_BUTTON_LISTENER_BEAN)
public class DeleteButtonListener implements ActionListener {

    public static final String DELETE_BUTTON_LISTENER_BEAN = "deleteButtonListener";
    private InvoiceTable invoiceTable;
    private List<InvoiceItem> invoiceItems;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String productName = validate();
            for (InvoiceItem invoiceItem : invoiceItems) {
                if (invoiceItem.getProduct().getProductName().equals(productName)) {
                    invoiceItems.remove(invoiceItem);
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException m) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        }catch (IllegalArgumentException m) {
            JOptionPane.showMessageDialog(null, m.getMessage());
        }
        invoiceTable.refreshModel();
    }

    public String validate() {
        if (invoiceTable.getValueAt(invoiceTable.getSelectedRow(), 0) == null) {
            throw new IllegalArgumentException("Column is empty");
        }
        return (String) invoiceTable.getValueAt(invoiceTable.getSelectedRow(), 0);
    }

    public void setInvoiceTable(InvoiceTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }

    public void setInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }
}
