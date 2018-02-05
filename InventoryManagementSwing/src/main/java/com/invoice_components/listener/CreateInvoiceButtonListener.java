package com.invoice_components.listener;

import com.constant.InvoiceType;
import com.dao.InvoiceDao;
import com.entity.Invoice;
import com.entity.InvoiceIN;
import com.entity.InvoiceItem;
import com.entity.InvoiceOUT;
import com.google.common.collect.ImmutableMap;
import com.invoice_components.combo_box.InvoiceTypeComboBoxModel;
import com.invoice_components.message.MessageProvider;
import com.invoice_components.table_model.InvoiceItemTableModel;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static com.invoice_components.listener.CreateInvoiceButtonListener.CREATE_INVOICE_BUTTON_LISTENER_BEAN;

@Component(CREATE_INVOICE_BUTTON_LISTENER_BEAN)
public class CreateInvoiceButtonListener implements ActionListener {

    @Resource(name = "invoiceDaoImpl")
    private InvoiceDao invoiceDao;
    private InvoiceItemTableModel invoiceItemTableModel;
    private InvoiceTypeComboBoxModel invoiceTypeComboBoxModel;
    private Map<InvoiceType, Invoice> invoiceMap = ImmutableMap.of(InvoiceType.IN, new InvoiceIN(),
            InvoiceType.OUT, new InvoiceOUT());
    public static final String CREATE_INVOICE_BUTTON_LISTENER_BEAN = "createInvoiceButtonListener";
    private static final String TAB_CHAR = "\t";
    private MessageProvider messageProvider = new MessageProvider();

    @Override
    public void actionPerformed(ActionEvent e) {
        if (invoiceTypeComboBoxModel.getSelectedItem() == null) {
            messageProvider.showMessage("You did not specify invoice type");
            return;
        }
        Invoice invoice = invoiceMap.get(invoiceTypeComboBoxModel.getSelectedItem());
        Set<InvoiceItem> invoiceItems = new HashSet<>(invoiceItemTableModel.getInvoiceItems());
        if (invoiceItems.isEmpty()) {
            messageProvider.showMessage("Your invoice is empty!");
            return;
        }
        StringBuilder message = invoiceInfo(invoiceItems);
        invoice.setInvoiceItems(invoiceItems);
        invoiceDao.saveInvoice(invoice);
        invoiceItemTableModel.resetModel();
        invoiceItemTableModel.fireTableDataChanged();
        messageProvider.showMessage(message.toString());
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

    public void setInvoiceDao(InvoiceDao invoiceDao) {
        this.invoiceDao = invoiceDao;
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void setInvoiceItemTableModel(InvoiceItemTableModel invoiceItemTableModel) {
        this.invoiceItemTableModel = invoiceItemTableModel;
    }

    public void setInvoiceTypeComboBoxModel(InvoiceTypeComboBoxModel invoiceTypeComboBoxModel) {
        this.invoiceTypeComboBoxModel = invoiceTypeComboBoxModel;
    }
}
