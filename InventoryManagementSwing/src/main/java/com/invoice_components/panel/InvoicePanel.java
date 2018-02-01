package com.invoice_components.panel;

import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;

public class InvoicePanel extends JPanel {

    private BorderLayout borderLayout = new BorderLayout();
    private InvoiceInputAndButtonPanel inputAndButtonPanel;
    private InvoiceTablePanel tablePanel;
    private CreateInvoicePanel createInvoicePanel;

    public InvoicePanel(ApplicationContext app) {
        setLayout(borderLayout);
        inputAndButtonPanel = new InvoiceInputAndButtonPanel(app);
        add(inputAndButtonPanel, BorderLayout.PAGE_START);
        tablePanel = new InvoiceTablePanel(app);
        add(tablePanel, BorderLayout.CENTER);
        inputAndButtonPanel.getAddButtonListener().setInvoiceTableModel(tablePanel.getInvoiceTableModel());
        inputAndButtonPanel.getDeleteButtonListener().setInvoiceTableModel(tablePanel.getInvoiceTableModel());
        inputAndButtonPanel.getDeleteButtonListener().setInvoiceTable(tablePanel.getInvoiceTable());
        createInvoicePanel = new CreateInvoicePanel(app);
        add(createInvoicePanel, BorderLayout.PAGE_END);
    }
}
