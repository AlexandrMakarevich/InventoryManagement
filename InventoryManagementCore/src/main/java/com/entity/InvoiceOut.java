package com.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@DiscriminatorValue("OUT")
public class InvoiceOut extends Invoice {

  private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceOut.class);

  @Override
  public Integer processProductQuantity(InvoiceItem invoiceItem, InventoryState inventoryState) {
    int count = inventoryState.getQuantity() - invoiceItem.getProductQuantity();
    if (count < 0) {
      String message = String.format("You want %s product with id %s, but we have only %s",
          invoiceItem.getProductQuantity(), invoiceItem.getProduct().getId(),
          inventoryState.getQuantity());
      LOGGER.info(message);
      throw new IllegalStateException(message);
    }
    return count;
  }
}
