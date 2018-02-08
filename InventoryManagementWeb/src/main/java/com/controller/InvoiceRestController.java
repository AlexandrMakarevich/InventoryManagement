package com.controller;

import com.dao.InvoiceDao;
import com.entity.InvoiceIN;
import com.entity.InvoiceOUT;
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
    public void createInvoiceIN(@RequestBody InvoiceIN invoiceIN) {
        invoiceDao.saveOrUpdateInvoice(invoiceIN);
    }

    @RequestMapping(value = "/createInvoiceOUT", method = RequestMethod.POST)
    public void createInvoiceOUT(@RequestBody InvoiceOUT invoiceOUT) {
        invoiceDao.saveOrUpdateInvoice(invoiceOUT);
    }
}
