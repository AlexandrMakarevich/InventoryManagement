package com;

import com.configuration.SpringConfig;
import com.invoice_components.panel.InvoicePanel;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        ApplicationContext app = new AnnotationConfigApplicationContext(SpringConfig.class);

        InvoicePanel invoicePanel = new InvoicePanel(app);
        JTabbedPane jTabbedPane = new JTabbedPane();
        jTabbedPane.addTab("InvoicePanel", invoicePanel);
        JFrame jFrame = new JFrame();
        jFrame.setSize(450, 300);
        jFrame.setContentPane(jTabbedPane);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
