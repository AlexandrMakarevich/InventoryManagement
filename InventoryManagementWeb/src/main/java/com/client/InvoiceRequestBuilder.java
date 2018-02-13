package com.client;

import com.constant.InvoiceType;
import com.dao.ProductDao;
import com.entity.*;
import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Repository;
import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Repository("invoiceRequestBuilder")
public class InvoiceRequestBuilder {

    private Map<InvoiceType, Invoice> typeInvoiceMap = ImmutableMap.of(
            InvoiceType.IN, new InvoiceIn(), InvoiceType.OUT, new InvoiceOut());

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    public Invoice buildInvoice(CreateInvoiceRequest createInvoice){
        Invoice invoice = typeInvoiceMap.get(createInvoice.getInvoiceType());
        Set<InvoiceItem> invoiceItemSet = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : createInvoice.getProductQuantityMap().entrySet()) {
            InvoiceItem invoiceItem = new InvoiceItem();
            Product product = productDao.getProductById(entry.getKey());
            invoiceItem.setProduct(product);
            invoiceItem.setProductQuantity(entry.getValue());
            invoiceItemSet.add(invoiceItem);
        }
        invoice.setInvoiceItems(invoiceItemSet);
        return invoice;
    }
}
