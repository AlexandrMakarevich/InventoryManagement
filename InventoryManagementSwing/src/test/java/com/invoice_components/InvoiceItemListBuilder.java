package com.invoice_components;

import com.entity.InvoiceItem;
import com.entity.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InvoiceItemListBuilder {

    public List<InvoiceItem> createInvoiceItemForTest(Map<Product, Integer> productQuantityMap) {
        List<InvoiceItem> invoiceItems = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : productQuantityMap.entrySet()) {
            InvoiceItem invoiceItem = new InvoiceItem();
            invoiceItem.setProduct(entry.getKey());
            invoiceItem.setProductQuantity(entry.getValue());
            invoiceItems.add(invoiceItem);
        }
        return invoiceItems;
    }
}
