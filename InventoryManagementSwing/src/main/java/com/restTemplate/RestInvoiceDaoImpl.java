package com.restTemplate;

import com.dao.InvoiceDao;
import com.entity.Invoice;
import com.entity.InvoiceIN;
import com.entity.InvoiceOUT;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("restInvoiceDaoImpl")
public class RestInvoiceDaoImpl extends BaseRestTemplate implements InvoiceDao {

    @Override
    public void saveOrUpdateInvoice(Invoice invoice) {
        if (invoice instanceof InvoiceIN) {
            restTemplate.postForObject(baseUrl + "/createInvoiceIN", invoice, InvoiceIN.class);
            return;
        }
        restTemplate.postForObject(baseUrl + "/createInvoiceOUT", invoice, InvoiceOUT.class);
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
