package com;

import com.builder.CreateInvoiceRequestBuilder;
import com.builder.ProductPersistentBuilder;
import com.client.CreateInvoiceRequest;
import com.controller.InvoiceController;
import com.dao.InvoiceDao;
import com.entity.Product;
import com.google.common.collect.ImmutableMap;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

public class TestInvoiceController extends BaseTest {

    @Resource(name = "invoiceController")
    private InvoiceController invoiceController;

    @Resource(name = "productPersistentBuilder")
    private ProductPersistentBuilder productPersistentBuilder;

    @Resource(name = "invoiceDaoImpl")
    private InvoiceDao invoiceDao;

    private CreateInvoiceRequestBuilder createInvoiceRequestBuilder;
    private Integer productQuantity = 12;

    @Before
    public void init() {
        createInvoiceRequestBuilder = new CreateInvoiceRequestBuilder();
    }


    @Test
    public void testBuildInvoice() {
        Product product = productPersistentBuilder.buildAndAddProduct();
        CreateInvoiceRequest createInvoiceRequest = createInvoiceRequestBuilder
                .withProductQuantityMap(ImmutableMap.of(product.getId(), productQuantity))
                .build();
//        Invoice expectedInvoice = invoiceController.createInvoice(createInvoiceRequest);
//        Invoice actualInvoice = invoiceDao.getInvoiceById(expectedInvoice.getId());
//        Assert.assertEquals("Actual result must be expected", expectedInvoice, actualInvoice);
    }
}
