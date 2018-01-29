package com.invoice_components.listener;

import com.dao.ProductDao;
import com.entity.InvoiceItem;
import com.entity.Product;
import com.invoice_components.combo_box.ProductComboBox;
import com.invoice_components.table.InvoiceTable;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static com.invoice_components.listener.AddButtonListener.ADD_BUTTON_LISTENER_BEAN;

@Component(ADD_BUTTON_LISTENER_BEAN)
public class AddButtonListener implements ActionListener {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    public static final String ADD_BUTTON_LISTENER_BEAN = "addButtonListener";
    private InvoiceTable invoiceTable;
    private JTextField quantityField;
    private ProductComboBox productComboBox;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Integer productQuantity = validateInputQuantity();
            Product product = validateAndInitializeProduct();
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(product);
            invoiceItem.setProductQuantity(productQuantity);
            invoiceTable.getInvoiceItems().add(invoiceItem);
            invoiceTable.refreshModel();
        } catch (NumberFormatException e1) {
            JOptionPane.showMessageDialog(null,
                    "You input wrong format of product quantity " + quantityField.getText());
        } catch (IllegalArgumentException message) {
            JOptionPane.showMessageDialog(null, message.getMessage());
        }
    }

    public Product validateAndInitializeProduct() {
        String productName = (String) productComboBox.getSelectedItem();
        for (InvoiceItem invoiceItem : invoiceTable.getInvoiceItems()) {
            if (invoiceItem.getProduct().getProductName().equals(productName)) {
                throw new IllegalArgumentException("You have already added product with name " + productName);
            }
        }
        return productDao.getProductByName(productName);
    }

    public Integer validateInputQuantity() {
        if (quantityField.getText().trim().isEmpty()) {
            throw new IllegalArgumentException("You did not specify the quantity of the product");
        }
        return Integer.valueOf(quantityField.getText().trim());
    }

    public void setInvoiceTable(InvoiceTable invoiceTable) {
        this.invoiceTable = invoiceTable;
    }

    public void setQuantityField(JTextField quantityField) {
        this.quantityField = quantityField;
    }

    public void setProductComboBox(ProductComboBox productComboBox) {
        this.productComboBox = productComboBox;
    }
}
