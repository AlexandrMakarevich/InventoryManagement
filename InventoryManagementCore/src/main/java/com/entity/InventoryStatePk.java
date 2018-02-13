package com.entity;

import com.date.JsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class InventoryStatePk implements Serializable {

  @OneToOne
  private Product product;

  @Column(name = "state_date")
  @JsonDeserialize(using = JsonDateDeserializer.class)
  private LocalDateTime stateDate = LocalDateTime.now();

  public Product getProduct() {
    return product;
  }

  public LocalDateTime getStateDate() {
    return stateDate;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public void setStateDate(LocalDateTime stateDate) {
    this.stateDate = stateDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    InventoryStatePk that = (InventoryStatePk) o;
    return Objects.equal(product, that.product)
        &&
        Objects.equal(stateDate, that.stateDate);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(product, stateDate);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("product", product)
        .add("stateDate", stateDate)
        .toString();
  }
}
