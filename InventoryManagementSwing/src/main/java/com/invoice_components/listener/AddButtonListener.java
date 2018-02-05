package com.invoice_components.listener;

import com.entity.InvoiceItem;
import com.entity.Product;
import com.invoice_components.combo_box.ItemProduct;
import com.invoice_components.combo_box.ProductComboBoxModel;
import com.invoice_components.message.MessageProvider;
import com.invoice_components.table_model.InvoiceItemTableModel;
import org.springframework.stereotype.Component;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.invoice_components.listener.AddButtonListener.ADD_BUTTON_LISTENER_BEAN;

@Component(ADD_BUTTON_LISTENER_BEAN)
public class AddButtonListener implements ActionListener {

    public static final String ADD_BUTTON_LISTENER_BEAN = "addButtonListener";
    private InvoiceItemTableModel invoiceItemTableModel;
    private JTextField quantityField;
    private ProductComboBoxModel productComboBoxModel;
    private MessageProvider messageProvider = new MessageProvider();

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Integer productQuantity = validateInputQuantity();
            Product product = validateAndInitializeProduct();
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(product);
            invoiceItem.setProductQuantity(productQuantity);
            invoiceItemTableModel.getInvoiceItems().add(invoiceItem);
            invoiceItemTableModel.fireTableDataChanged();
        } catch (NumberFormatException e1) {
            messageProvider.showMessage(
                    "You input wrong format of product quantity " + quantityField.getText());
        } catch (IllegalArgumentException message) {
            messageProvider.showMessage(message.getMessage());
        } catch (NullPointerException m) {
            messageProvider.showMessage("You did not specify the product");
        }
    }

    public Product validateAndInitializeProduct() {
        ItemProduct itemProduct = (ItemProduct) productComboBoxModel.getSelectedItem();
        for (InvoiceItem invoiceItem : invoiceItemTableModel.getInvoiceItems()) {
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

    public void setInvoiceItemTableModel(InvoiceItemTableModel invoiceItemTableModel) {
        this.invoiceItemTableModel = invoiceItemTableModel;
    }

    public void setQuantityField(JTextField quantityField) {
        this.quantityField = quantityField;
    }

    public void setProductComboBoxModel(ProductComboBoxModel productComboBoxModel) {
        this.productComboBoxModel = productComboBoxModel;
    }

    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }
}
