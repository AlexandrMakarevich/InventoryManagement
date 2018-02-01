package com.invoice_components.panel;

import com.invoice_components.combo_box.InvoiceTypeComboBoxModel;
import com.invoice_components.listener.CreateInvoiceButtonListener;
import com.invoice_components.table_model.InvoiceTableModel;
import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;
import static com.invoice_components.combo_box.InvoiceTypeComboBoxModel.INVOICE_TYPE_COMBO_BOX_MODEL_BEAN;
import static com.invoice_components.listener.CreateInvoiceButtonListener.CREATE_INVOICE_BUTTON_LISTENER_BEAN;
import static com.invoice_components.table_model.InvoiceTableModel.INVOICE_TABLE_MODEL_BEAN;

public class CreateInvoicePanel extends JPanel{

    private FlowLayout layout = new FlowLayout();
    private JButton createInvoiceButton = new JButton("CreateInvoice");
    private InvoiceTypeComboBoxModel invoiceTypeComboBoxModel;
    private JComboBox invoiceTypeComboBox;
    private JLabel label = new JLabel("Choose invoice type :");
    private InvoiceTableModel invoiceTableModel;
    private CreateInvoiceButtonListener createInvoiceButtonListener;

    public CreateInvoicePanel(ApplicationContext app) {
        setLayout(layout);
        invoiceTypeComboBoxModel = (InvoiceTypeComboBoxModel) app.getBean(INVOICE_TYPE_COMBO_BOX_MODEL_BEAN);
        invoiceTypeComboBox = new JComboBox(invoiceTypeComboBoxModel);
        add(label);
        add(invoiceTypeComboBox);
        add(createInvoiceButton);
        invoiceTableModel = (InvoiceTableModel) app.getBean(INVOICE_TABLE_MODEL_BEAN);
        createInvoiceButtonListener = (CreateInvoiceButtonListener) app.getBean(CREATE_INVOICE_BUTTON_LISTENER_BEAN);
        createInvoiceButtonListener.setInvoiceTypeComboBoxModel(invoiceTypeComboBoxModel);
        createInvoiceButtonListener.setInvoiceTableModel(invoiceTableModel);
        createInvoiceButton.addActionListener(createInvoiceButtonListener);
    }
}
