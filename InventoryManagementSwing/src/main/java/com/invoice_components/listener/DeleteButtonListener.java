package com.invoice_components.listener;

import com.entity.InvoiceItem;
import com.invoice_components.message.MessageProvider;
import com.invoice_components.table_model.InvoiceItemTableModel;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.invoice_components.listener.DeleteButtonListener.DELETE_BUTTON_LISTENER_BEAN;

@Component(DELETE_BUTTON_LISTENER_BEAN)
public class DeleteButtonListener implements ActionListener {

    public static final String DELETE_BUTTON_LISTENER_BEAN = "deleteButtonListener";
    private JTable invoiceTable;
    private MessageProvider messageProvider = new MessageProvider();

    @Override
    public void actionPerformed(ActionEvent e) {
        Integer selectedIndex = invoiceTable.getSelectedRow();
        String productName = null;
        try {
            productName = (String) getInvoiceItemTableModel().getValueAt(selectedIndex, 0);
        } catch (ArrayIndexOutOfBoundsException m) {
            messageProvider.showMessage("You did not choose what to delete!");
        }
        for (InvoiceItem invoiceItem : getInvoiceItemTableModel().getInvoiceItems()) {
            if (invoiceItem.getProduct().getProductName().equals(productName)) {
                getInvoiceItemTableModel().getInvoiceItems().remove(invoiceItem);
                break;
            }
        }
        getInvoiceItemTableModel().fireTableDataChanged();
    }

    private InvoiceItemTableModel getInvoiceItemTableModel(){
        return (InvoiceItemTableModel) invoiceTable.getModel();
    }

    public void setInvoiceTable(JTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }
}
