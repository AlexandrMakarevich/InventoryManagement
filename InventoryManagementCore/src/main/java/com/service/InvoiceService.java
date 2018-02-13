package com.service;

import com.constant.InvoiceStatus;
import com.dao.InvoiceDao;
import com.entity.Invoice;
import com.process.ProcessInvoice;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("invoiceService")
/**
 * This is invoice service class
 *
 * @author Alexandr
 */
public class InvoiceService {

  @Resource(name = "invoiceDaoImpl")
  private InvoiceDao invoiceDao;

  @Resource(name = "processInvoice")
  private ProcessInvoice processInvoice;

  /**
   * This is method processed invoices with status PENDING,
   * if all is correct status will be changed on COMPLETE,
   * if some thing wrong happen, status will be ERROR.
   */
  @Transactional
  public void processPendingInvoices() {
    List<Invoice> invoicePendingList = invoiceDao.getAllPendingInvoice();
    for (Invoice invoice : invoicePendingList) {
      try {
        processInvoice.process(invoice);
        invoice.setStatus(InvoiceStatus.COMPLETE);
      } catch (Exception e) {
        invoice.setStatus(InvoiceStatus.ERROR);
      }
      invoiceDao.saveOrUpdateInvoice(invoice);
    }
  }
}
