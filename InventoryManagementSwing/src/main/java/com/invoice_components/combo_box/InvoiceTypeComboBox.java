package com.invoice_components.combo_box;

import com.constant.InvoiceType;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.swing.*;
import static com.invoice_components.combo_box.InvoiceTypeComboBox.INVOICE_TYPE_COMBO_BOX_BEAN;

@Component(INVOICE_TYPE_COMBO_BOX_BEAN)
public class InvoiceTypeComboBox extends JComboBox<InvoiceType> {

    public static final String INVOICE_TYPE_COMBO_BOX_BEAN = "invoiceTypeComboBox";

    @PostConstruct
    public void init() {
        addItem(InvoiceType.IN);
        addItem(InvoiceType.OUT);
    }
}
