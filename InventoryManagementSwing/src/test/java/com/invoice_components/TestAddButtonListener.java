package com.invoice_components;

import com.builder.ProductBuilder;
import com.entity.InvoiceItem;
import com.entity.Product;
import com.google.common.collect.ImmutableMap;
import com.invoice_components.combo_box.ItemProduct;
import com.invoice_components.combo_box.ProductComboBoxModel;
import com.invoice_components.listener.AddButtonListener;
import com.invoice_components.message.MessageProvider;
import com.invoice_components.table_model.InvoiceItemTableModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestAddButtonListener {

    private AddButtonListener addButtonListener;
    private InvoiceItemTableModel invoiceItemTableModel;
    private ProductComboBoxModel productComboBoxModel;
    private ProductBuilder productBuilder;
    private JTextField quantityField;

    @Mock
    private MessageProvider messageProvider = Mockito.mock(MessageProvider.class);

    @Before
    public void init() {
        productBuilder = new ProductBuilder();
        invoiceItemTableModel = new InvoiceItemTableModel();
        addButtonListener = new AddButtonListener();
        productComboBoxModel = new ProductComboBoxModel();
        quantityField = new JTextField();
        addButtonListener.setQuantityField(quantityField);
        addButtonListener.setInvoiceItemTableModel(invoiceItemTableModel);
        addButtonListener.setProductComboBoxModel(productComboBoxModel);
        addButtonListener.setMessageProvider(messageProvider);
    }

    @Test
    public void testAddOneProductAndQuantityInTable() {
        Integer productQuantity = 2;
        Product product = productBuilder.withId(3).build();

        List<InvoiceItem> expectedInvoiceItems =
                createInvoiceItemForTest(ImmutableMap.of(product, productQuantity));

        List<InvoiceItem> actualInvoiceItems = invoiceItemTableModel.getInvoiceItems();
        Assert.assertEquals("Actual result must be expected", actualInvoiceItems, expectedInvoiceItems);
    }

    @Test
    public void testAddTwoProductAndQuantityInTable() {
        Integer quantityForFirstProduct = 2;
        Integer quantityForSecondProduct = 10;
        Product firstProduct = productBuilder.withId(4).build();
        productBuilder.reset();
        Product secondProduct = productBuilder.withId(7).build();

        List<InvoiceItem> expectedInvoiceItems =
                createInvoiceItemForTest(ImmutableMap.of(firstProduct, quantityForFirstProduct,
                        secondProduct, quantityForSecondProduct));

        List<InvoiceItem> actualInvoiceItems = invoiceItemTableModel.getInvoiceItems();
        Assert.assertEquals("Actual result must be expected", actualInvoiceItems, expectedInvoiceItems);
    }

    @Test
    public void testWhenUserNotEnterQuantity() {
        Product product = productBuilder.withId(4).build();
        productComboBoxModel.setSelectedItem(new ItemProduct(product));
        addButtonListener.actionPerformed(null);
        assertThat(invoiceItemTableModel.getInvoiceItems().size(), is(0));
    }

    public List<InvoiceItem> createInvoiceItemForTest(Map<Product, Integer> productQuantityMap) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
            productComboBoxModel.setSelectedItem(new ItemProduct(entry.getKey()));
            quantityField.setText(entry.getValue().toString());
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(entry.getKey());
            invoiceItem.setProductQuantity(entry.getValue());
            invoiceItems.add(invoiceItem);
            addButtonListener.actionPerformed(null);
        }
        return invoiceItems;
    }
}
