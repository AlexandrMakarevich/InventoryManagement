package com.invoice_components.listener;

import com.entity.InvoiceItem;
import com.entity.Product;
import com.invoice_components.combo_box.ItemProduct;
import com.invoice_components.combo_box.ProductComboBoxModel;
import com.invoice_components.table_model.InvoiceTableModel;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.invoice_components.listener.AddButtonListener.ADD_BUTTON_LISTENER_BEAN;

@Component(ADD_BUTTON_LISTENER_BEAN)
public class AddButtonListener implements ActionListener {

    public static final String ADD_BUTTON_LISTENER_BEAN = "addButtonListener";
    private InvoiceTableModel invoiceTableModel;
    private JTextField quantityField;
    private ProductComboBoxModel productComboBoxModel;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Integer productQuantity = validateInputQuantity();
            Product product = validateAndInitializeProduct();
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(product);
            invoiceItem.setProductQuantity(productQuantity);
            invoiceTableModel.getInvoiceItems().add(invoiceItem);
            invoiceTableModel.fireTableDataChanged();
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                    "You input wrong format of product quantity " + quantityField.getText());
        } catch (IllegalArgumentException message) {
            JOptionPane.showMessageDialog(null, message.getMessage());
        } catch (NullPointerException m) {
            JOptionPane.showMessageDialog(null, "You did not specify the product");
        }
    }

    public Product validateAndInitializeProduct() {
        ItemProduct itemProduct = (ItemProduct) productComboBoxModel.getSelectedItem();
        for (InvoiceItem invoiceItem : invoiceTableModel.getInvoiceItems()) {
            if (invoiceItem.getProduct().equals(itemProduct.getProduct())) {
                throw new IllegalArgumentException("You have already added product with name " + itemProduct.getProduct().getProductName());
            }
        }
        return itemProduct.getProduct();
    }

    public Integer validateInputQuantity() {
        if (quantityField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("You did not specify the quantity of the product");
        }
        return Integer.valueOf(quantityField.getText().trim());
    }

    public void setInvoiceTableModel(InvoiceTableModel invoiceTableModel) {
        this.invoiceTableModel = invoiceTableModel;
    }

    public void setQuantityField(JTextField quantityField) {
        this.quantityField = quantityField;
    }

    public void setProductComboBoxModel(ProductComboBoxModel productComboBoxModel) {
        this.productComboBoxModel = productComboBoxModel;
    }
}
