package com.invoice_components.panel;

import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;

public class InvoicePanel extends JPanel {

    private BorderLayout borderLayout = new BorderLayout();
    private InputAndButtonPanel inputAndButtonPanel;
    private TablePanel tablePanel;

    public InvoicePanel(ApplicationContext app) {
        setLayout(borderLayout);
        inputAndButtonPanel = new InputAndButtonPanel(app);
        add(inputAndButtonPanel, BorderLayout.PAGE_START);
        tablePanel = new TablePanel(app);
        add(tablePanel, BorderLayout.PAGE_END);
        inputAndButtonPanel.getAddButtonListener().setInvoiceTable(tablePanel.getInvoiceTable());
        inputAndButtonPanel.getAddButtonListener().setInvoiceItems(tablePanel.getInvoiceTable().getInvoiceItems());
        inputAndButtonPanel.getDeleteButtonListener().setInvoiceTable(tablePanel.getInvoiceTable());
        inputAndButtonPanel.getDeleteButtonListener().setInvoiceItems(tablePanel.getInvoiceTable().getInvoiceItems());
    }
}
