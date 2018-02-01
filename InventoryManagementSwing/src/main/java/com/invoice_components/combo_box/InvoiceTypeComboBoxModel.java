package com.invoice_components.combo_box;

import com.constant.InvoiceType;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.util.List;
import static com.invoice_components.combo_box.InvoiceTypeComboBoxModel.INVOICE_TYPE_COMBO_BOX_MODEL_BEAN;

@Component(INVOICE_TYPE_COMBO_BOX_MODEL_BEAN)
public class InvoiceTypeComboBoxModel extends AbstractListModel implements ComboBoxModel {

    public static final String INVOICE_TYPE_COMBO_BOX_MODEL_BEAN = "invoiceTypeComboBoxModel";
    private List<InvoiceType> invoiceTypes = ImmutableList.of(InvoiceType.IN, InvoiceType.OUT);
    private InvoiceType selectedInvoiceType;

    @Override
    public void setSelectedItem(Object anItem) {
        selectedInvoiceType = (InvoiceType) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedInvoiceType;
    }

    @Override
    public int getSize() {
        return invoiceTypes.size();
    }

    @Override
    public Object getElementAt(int index) {
        return invoiceTypes.get(index);
    }
}
