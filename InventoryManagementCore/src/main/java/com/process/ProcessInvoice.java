package com.process;

import com.dao.InventoryStateDao;
import com.entity.InventoryState;
import com.entity.InventoryStatePk;
import com.entity.Invoice;
import com.entity.InvoiceItem;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("processInvoice")
public class ProcessInvoice {

  @Resource(name = "inventoryStateDaoImpl")
  private InventoryStateDao inventoryStateDao;

  private static final Logger LOGGER = LoggerFactory.getLogger(ProcessInvoice.class);

  /**
   * This method is processed invoice ,in depends of type invoice.
   *
   * @param invoice - invoice,which need be processed
   */
  public void process(Invoice invoice) {
    if (invoice.getInvoiceItems().size() == 0) {
      LOGGER.info("Invoice must have at least one product");
      throw new IllegalStateException("Invoice must have at least one product");
    }
    List<Integer> productIdsList = getAllProductId(invoice.getInvoiceItems());
    List<InventoryState> inventoryStateList = inventoryStateDao.getInventoryStates(productIdsList);
    for (InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
      buildAndPersistInventoryState(invoice, inventoryStateList, invoiceItem);
    }
  }

  private void buildAndPersistInventoryState(Invoice invoice,
                                             List<InventoryState> inventoryStateList,
                                             InvoiceItem invoiceItem) {
    InventoryState inventoryState = Iterables.find(inventoryStateList,
        new Predicate<InventoryState>() {
          @Override
          public boolean apply(InventoryState input) {
            return input.getInventoryStatePk().getProduct().equals(invoiceItem.getProduct());
          }
        });
    InventoryState inventoryState1 = new InventoryState();
    inventoryState1.setInventoryStatePk(new InventoryStatePk());
    inventoryState1.getInventoryStatePk().setStateDate(LocalDateTime.now());
    inventoryState1.getInventoryStatePk().setProduct(
        inventoryState.getInventoryStatePk().getProduct());
    inventoryState1.setQuantity(invoice.processProductQuantity(invoiceItem, inventoryState));
    inventoryStateDao.saveInventoryState(inventoryState1);
  }

  /**
   * Method will return all id of product,which contains in invoice.
   *
   * @param invoiceItems - invoiceItems collection,which contains in invoice
   *
   * @return list all id of product
   */
  public List<Integer> getAllProductId(Set<InvoiceItem> invoiceItems) {
    Iterable<Integer> listOfProductId =
        Iterables.transform(invoiceItems, new Function<InvoiceItem, Integer>() {
          @Override
          public Integer apply(InvoiceItem input) {
            return input.getProduct().getId();
          }
        });
    return ImmutableList.copyOf(listOfProductId);
  }
}
