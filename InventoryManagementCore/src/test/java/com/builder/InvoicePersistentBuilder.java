package com.builder;

import com.constant.InvoiceType;
import com.dao.InvoiceDao;
import com.entity.Invoice;
import com.entity.InvoiceItem;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Repository("invoicePersistentBuilder")
public class InvoicePersistentBuilder {

    @Resource(name = "invoiceItemPersistentBuilder")
    private InvoiceItemPersistentBuilder invoiceItemPersistentBuilder;

    @Resource(name = "invoiceDaoImpl")
    private InvoiceDao invoiceDao;

    private InvoiceBuilder invoiceBuilder = new InvoiceBuilder(InvoiceType.IN);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Invoice buildAndAddInvoice() {
        InvoiceItem invoiceItem = invoiceItemPersistentBuilder.buildAndAddInvoiceItem();
        Set<InvoiceItem> invoiceItems = new HashSet<>();
        invoiceItems.add(invoiceItem);
        Invoice invoice = invoiceBuilder.withSetInvoiceItems(invoiceItems).build();
        invoiceDao.saveOrUpdateInvoice(invoice);
        return invoice;
    }
}
