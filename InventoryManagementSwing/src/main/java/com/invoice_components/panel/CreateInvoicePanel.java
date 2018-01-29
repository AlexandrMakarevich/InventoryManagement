package com.invoice_components.panel;

import com.invoice_components.combo_box.InvoiceTypeComboBox;
import com.invoice_components.listener.CreateInvoiceButtonListener;
import com.invoice_components.table.InvoiceTable;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.combo_box.InvoiceTypeComboBox.INVOICE_TYPE_COMBO_BOX_BEAN;
import static com.invoice_components.listener.CreateInvoiceButtonListener.CREATE_INVOICE_BUTTON_LISTENER_BEAN;
import static com.invoice_components.table.InvoiceTable.INVOICE_TABLE_BEAN;

public class CreateInvoicePanel extends JPanel{

    private FlowLayout layout = new FlowLayout();
    private Button createInvoiceButton = new Button("CreateInvoice");
    private InvoiceTypeComboBox invoiceTypeComboBox;
    private JLabel label = new JLabel("Chose invoice type :");
    private InvoiceTable invoiceTable;
    private CreateInvoiceButtonListener createInvoiceButtonListener;

    public CreateInvoicePanel(ApplicationContext app) {
        setLayout(layout);
        invoiceTypeComboBox = (InvoiceTypeComboBox) app.getBean(INVOICE_TYPE_COMBO_BOX_BEAN);
        add(label);
        add(invoiceTypeComboBox);
        add(createInvoiceButton);
        invoiceTable = (InvoiceTable) app.getBean(INVOICE_TABLE_BEAN);
        createInvoiceButtonListener = (CreateInvoiceButtonListener) app.getBean(CREATE_INVOICE_BUTTON_LISTENER_BEAN);
        createInvoiceButtonListener.setInvoiceTypeComboBox(invoiceTypeComboBox);
        createInvoiceButtonListener.setInvoiceTable(invoiceTable);
        createInvoiceButton.addActionListener(createInvoiceButtonListener);
    }
}
