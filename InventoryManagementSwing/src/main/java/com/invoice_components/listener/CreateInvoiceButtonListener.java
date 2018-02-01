package com.invoice_components.listener;

import com.constant.InvoiceType;
import com.dao.InvoiceDao;
import com.entity.Invoice;
import com.entity.InvoiceIN;
import com.entity.InvoiceItem;
import com.entity.InvoiceOUT;
import com.google.common.collect.ImmutableMap;
import com.invoice_components.combo_box.InvoiceTypeComboBoxModel;
import com.invoice_components.table_model.InvoiceTableModel;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static com.invoice_components.listener.CreateInvoiceButtonListener.CREATE_INVOICE_BUTTON_LISTENER_BEAN;

@Component(CREATE_INVOICE_BUTTON_LISTENER_BEAN)
public class CreateInvoiceButtonListener implements ActionListener {

    @Resource(name = "invoiceDaoImpl")
    private InvoiceDao invoiceDao;
    private InvoiceTableModel invoiceTableModel;
    private InvoiceTypeComboBoxModel invoiceTypeComboBoxModel;
    private Map<InvoiceType, Invoice> invoiceMap = ImmutableMap.of(InvoiceType.IN, new InvoiceIN(),
            InvoiceType.OUT, new InvoiceOUT());
    public static final String CREATE_INVOICE_BUTTON_LISTENER_BEAN = "createInvoiceButtonListener";
    private static final String TAB_CHAR = "\t";

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Invoice invoice = invoiceMap.get(validate());
            Set<InvoiceItem> invoiceItems = new HashSet<>(invoiceTableModel.getInvoiceItems());
            if (invoiceItems.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Your invoice is empty!");
                return;
            }
            StringBuilder message = invoiceInfo(invoiceItems);
            invoice.setInvoiceItems(invoiceItems);
            invoiceDao.saveInvoice(invoice);
            invoiceTableModel.setInvoiceItems(new ArrayList<>());
            invoiceTableModel.fireTableDataChanged();
            JOptionPane.showMessageDialog(null, message);
        } catch (IllegalArgumentException e1) {
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
    }

    public InvoiceType validate() {
        if (invoiceTypeComboBoxModel.getSelectedItem() == null) {
            throw new IllegalArgumentException("You did not specify invoice type");
        }
        return (InvoiceType) invoiceTypeComboBoxModel.getSelectedItem();
    }

    public StringBuilder invoiceInfo(Set<InvoiceItem> invoiceItems){
        StringBuilder message = new StringBuilder("Your invoice is : ");
        for (InvoiceItem invoiceItem : invoiceItems){
            message.append(TAB_CHAR)
                    .append(" product - ' " + invoiceItem.getProduct().getProductName() + " '")
                    .append(" with quantity - ' " + invoiceItem.getProductQuantity() + " ';")
                    .append(TAB_CHAR);
        }
        return message;
    }

    public void setInvoiceTableModel(InvoiceTableModel invoiceTableModel) {
        this.invoiceTableModel = invoiceTableModel;
    }

    public void setInvoiceTypeComboBoxModel(InvoiceTypeComboBoxModel invoiceTypeComboBoxModel) {
        this.invoiceTypeComboBoxModel = invoiceTypeComboBoxModel;
    }
}
