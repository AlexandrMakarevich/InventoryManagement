package com.controller;

import com.dao.InvoiceDao;
import com.entity.InvoiceIn;
import com.entity.InvoiceOut;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

@RestController
public class InvoiceRestController {

    @Resource(name = "invoiceDaoImpl")
    private InvoiceDao invoiceDao;

    @RequestMapping(value = "/createInvoiceIN", method = RequestMethod.POST)
    public void createInvoiceIN(@RequestBody InvoiceIn invoiceIn) {
        invoiceDao.saveOrUpdateInvoice(invoiceIn);
    }

    @RequestMapping(value = "/createInvoiceOUT", method = RequestMethod.POST)
    public void createInvoiceOUT(@RequestBody InvoiceOut invoiceOut) {
        invoiceDao.saveOrUpdateInvoice(invoiceOut);
    }
}
