package com.service;

import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("invoicePendingJob")
public class InvoicePendingJob {

  @Resource(name = "invoiceService")
  private InvoiceService invoiceService;

  @Scheduled(fixedDelay = 300000)
  public void processPendingInvoiceJob() {
    invoiceService.processPendingInvoices();
  }
}
