package com.invoice_components;

import com.builder.ProductBuilder;
import com.constant.InvoiceType;
import com.dao.InvoiceDaoImpl;
import com.entity.Invoice;
import com.entity.InvoiceIn;
import com.entity.InvoiceItem;
import com.entity.Product;
import com.google.common.collect.ImmutableMap;
import com.invoice_components.combo_box.InvoiceTypeComboBoxModel;
import com.invoice_components.listener.CreateInvoiceButtonListener;
import com.invoice_components.message.MessageProvider;
import com.invoice_components.table_model.InvoiceItemTableModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.List;

public class TestCreateInvoiceButtonListener {

    private ProductBuilder productBuilder;
    private InvoiceItemTableModel invoiceItemTableModel;
    private InvoiceTypeComboBoxModel invoiceTypeComboBoxModel;
    private CreateInvoiceButtonListener createInvoiceButtonListener;
    private InvoiceItemListBuilder invoiceItemListBuilder;

    private MessageProvider messageProvider = Mockito.mock(MessageProvider.class);

    private InvoiceDaoImpl invoiceDao = Mockito.mock(InvoiceDaoImpl.class);

    @Before
    public void init() {
        productBuilder = new ProductBuilder();
        invoiceItemListBuilder = new InvoiceItemListBuilder();
        createInvoiceButtonListener = new CreateInvoiceButtonListener();
        invoiceTypeComboBoxModel = new InvoiceTypeComboBoxModel();
        invoiceItemTableModel = new InvoiceItemTableModel();
        createInvoiceButtonListener.setInvoiceItemTableModel(invoiceItemTableModel);
        createInvoiceButtonListener.setInvoiceTypeComboBoxModel(invoiceTypeComboBoxModel);
        createInvoiceButtonListener.setMessageProvider(messageProvider);
        createInvoiceButtonListener.setInvoiceDao(invoiceDao);
    }

    @Test
    public void testDeleteProductFromTable() {
        Product product = productBuilder.withId(2).build();
        Integer productQuantity = 2;

        List<InvoiceItem> invoiceItems = invoiceItemListBuilder.createInvoiceItemForTest(
                ImmutableMap.of(product, productQuantity));
        invoiceItemTableModel.setInvoiceItems(invoiceItems);
        invoiceTypeComboBoxModel.setSelectedItem(InvoiceType.IN);
        createInvoiceButtonListener.actionPerformed(null);

        Invoice invoice = new InvoiceIn();
        invoice.setInvoiceItems(new HashSet<>(invoiceItems));
        Mockito.verify(invoiceDao, Mockito.times(1)).saveOrUpdateInvoice(invoice);

        StringBuilder message = createInvoiceButtonListener.invoiceInfo(new HashSet<>(invoiceItems));
        Mockito.verify(messageProvider, Mockito.times(1)).showMessage(message.toString());

        Assert.assertTrue("InvoiceItem list must be empty",invoiceItemTableModel.getInvoiceItems().isEmpty());
    }
}
