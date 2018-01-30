package com.invoice_components.panel;

import org.springframework.context.ApplicationContext;
import javax.swing.*;
import java.awt.*;

public class ReportPanel extends JPanel {

    private BorderLayout borderLayout = new BorderLayout();
    private ReportTablePanel reportTablePanel;
    private ReportButtonPanel reportButtonPanel;

    public ReportPanel(ApplicationContext app) {
        setLayout(borderLayout);
        reportButtonPanel = new ReportButtonPanel(app);
        add(reportButtonPanel, BorderLayout.PAGE_START);
        reportTablePanel = new ReportTablePanel(app);
        add(reportTablePanel, BorderLayout.PAGE_END);
    }
}
