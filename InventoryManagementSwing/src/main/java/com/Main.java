package com;

import com.configuration.SpringConfig;
import com.invoice_components.panel.InvoicePanel;
import com.invoice_components.panel.ReportPanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);

        InvoicePanel invoicePanel = new InvoicePanel(app);
        ReportPanel reportPanel = new ReportPanel(app);
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("InvoicePanel", invoicePanel);
        jTabbedPane.addTab("ReportPanel", reportPanel);
        JFrame jFrame = new JFrame();
        jFrame.setSize(650, 300);
        jFrame.setContentPane(jTabbedPane);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
