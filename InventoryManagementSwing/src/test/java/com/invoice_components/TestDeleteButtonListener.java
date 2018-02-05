package com.invoice_components;

import com.builder.ProductBuilder;
import com.entity.Product;
import com.google.common.collect.ImmutableMap;
import com.invoice_components.listener.DeleteButtonListener;
import com.invoice_components.table_model.InvoiceItemTableModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import javax.swing.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestDeleteButtonListener {

    private DeleteButtonListener deleteButtonListener;
    private InvoiceItemTableModel invoiceItemTableModel;
    private ProductBuilder productBuilder;
    private InvoiceItemListBuilder invoiceItemListBuilder;

    @Mock
    private JTable invoiceTable = Mockito.mock(JTable.class);

    @Before
    public void init() {
        productBuilder = new ProductBuilder();
        invoiceItemListBuilder = new InvoiceItemListBuilder();
        deleteButtonListener = new DeleteButtonListener();
        deleteButtonListener.setInvoiceTable(invoiceTable);
        invoiceItemTableModel = new InvoiceItemTableModel();
        Mockito.when(invoiceTable.getModel()).thenReturn(invoiceItemTableModel);
    }

    @Test
    public void testDeleteDataFromTableWhenThereIsOneProduct() {
        Product product = productBuilder.withId(5).build();
        Integer productQuantity = 19;

        invoiceItemTableModel.setInvoiceItems(
                invoiceItemListBuilder.createInvoiceItemForTest(ImmutableMap.of(product, productQuantity)));
        Mockito.when(invoiceTable.getSelectedRow()).thenReturn(0);
        deleteButtonListener.actionPerformed(null);
        assertThat(invoiceItemTableModel.getInvoiceItems().size(), is(0));
    }

    @Test
    public void testDeleteDataFromTableWhenThereIsTwoProduct() {
        Integer quantityForFirstProduct = 3;
        Integer quantityForSecondProduct = 18;
        Product firstProduct = productBuilder.withId(3).build();
        productBuilder.reset();
        Product secondProduct = productBuilder.withId(5).build();
        invoiceItemTableModel.setInvoiceItems(invoiceItemListBuilder.createInvoiceItemForTest(
                ImmutableMap.of(firstProduct, quantityForFirstProduct, secondProduct, quantityForSecondProduct)));
        Mockito.when(invoiceTable.getSelectedRow()).thenReturn(1);
        deleteButtonListener.actionPerformed(null);
        Assert.assertEquals("invoiceItemTableModel must contain only one product",
                invoiceItemTableModel.getInvoiceItems().size(), 1);
    }
}
