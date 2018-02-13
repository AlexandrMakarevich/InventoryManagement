package com.entity;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "inventory_state")
public class InventoryState {

  @EmbeddedId
  private InventoryStatePk inventoryStatePk;

  @Column(name = "quantity")
  private Integer quantity;

  public BigDecimal calculateItemCost() {
    BigDecimal bigDecimalQuantity = BigDecimal.valueOf(quantity);
    return inventoryStatePk.getProduct().getPrice().multiply(bigDecimalQuantity);
  }

  public InventoryStatePk getInventoryStatePk() {
    return inventoryStatePk;
  }

  public void setInventoryStatePk(InventoryStatePk inventoryStatePk) {
    this.inventoryStatePk = inventoryStatePk;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryState that = (InventoryState) o;
    return Objects.equal(inventoryStatePk, that.inventoryStatePk);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(inventoryStatePk);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("inventoryStatePk", inventoryStatePk)
        .add("quantity", quantity)
        .toString();
  }
}
