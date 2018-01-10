package com.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service("invoicePendingJob")
public class InvoicePendingJob {

    @Resource(name = "invoiceService")
    private InvoiceService invoiceService;

    @Scheduled(fixedDelay = 300000)
    public void processPendingInvoiceJob() {
        invoiceService.processPendingInvoices();
    }
}
