package com.invoice_components.listener;

import com.entity.InvoiceItem;
import com.invoice_components.table_model.InvoiceTableModel;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.invoice_components.listener.DeleteButtonListener.DELETE_BUTTON_LISTENER_BEAN;

@Component(DELETE_BUTTON_LISTENER_BEAN)
public class DeleteButtonListener implements ActionListener {

    public static final String DELETE_BUTTON_LISTENER_BEAN = "deleteButtonListener";
    private InvoiceTableModel invoiceTableModel;
    private JTable invoiceTable;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String productName = validate();
            for (InvoiceItem invoiceItem : invoiceTableModel.getInvoiceItems()) {
                if (invoiceItem.getProduct().getProductName().equals(productName)) {
                    invoiceTableModel.getInvoiceItems().remove(invoiceItem);
                    break;
                }
            }
        }catch (ArrayIndexOutOfBoundsException m) {
            JOptionPane.showMessageDialog(null, "You did not choose what to delete!");
        }catch (IllegalArgumentException m) {
            JOptionPane.showMessageDialog(null, m.getMessage());
        }
        invoiceTableModel.fireTableDataChanged();
    }

    public String validate() {
        if (invoiceTableModel.getValueAt(invoiceTable.getSelectedRow(), 0) == null) {
            throw new IllegalArgumentException("Column is empty");
        }
        return (String) invoiceTableModel.getValueAt(invoiceTable.getSelectedRow(), 0);
    }

    public InvoiceTableModel getInvoiceTableModel() {
        return invoiceTableModel;
    }

    public void setInvoiceTableModel(InvoiceTableModel invoiceTableModel) {
        this.invoiceTableModel = invoiceTableModel;
    }

    public JTable getInvoiceTable() {
        return invoiceTable;
    }

    public void setInvoiceTable(JTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }
}
