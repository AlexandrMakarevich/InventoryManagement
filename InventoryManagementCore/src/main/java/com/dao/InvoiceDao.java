package com.dao;

import com.entity.Invoice;
import java.util.List;

public interface InvoiceDao {

  void saveOrUpdateInvoice(Invoice invoice);

  Invoice getInvoiceById(int id);

  List<Invoice> getAllPendingInvoice();
}
