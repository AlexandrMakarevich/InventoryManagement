package com.restTemplate;

import com.dao.InvoiceDao;
import com.entity.Invoice;
import com.entity.InvoiceIn;
import com.entity.InvoiceOut;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("restInvoiceDaoImpl")
public class RestInvoiceDaoImpl extends BaseRestTemplate implements InvoiceDao {

    @Override
    public void saveOrUpdateInvoice(Invoice invoice) {
        if (invoice instanceof InvoiceIn) {
            restTemplate.postForObject(baseUrl + "/createInvoiceIN", invoice, InvoiceIn.class);
            return;
        }
        restTemplate.postForObject(baseUrl + "/createInvoiceOUT", invoice, InvoiceOut.class);
    }

    @Override
    public Invoice getInvoiceById(int id) {
        return null;
    }

    @Override
    public List<Invoice> getAllPendingInvoice() {
        return null;
    }
}
