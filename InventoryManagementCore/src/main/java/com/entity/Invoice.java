package com.entity;

import com.constant.InvoiceStatus;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "invoice")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", columnDefinition = "ENUM('IN', 'OUT')")
public abstract class Invoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "ENUM('PENDING', 'COMPLETE', 'ERROR')")
  private InvoiceStatus status = InvoiceStatus.PENDING;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "invoice_item_map",
      inverseJoinColumns = @JoinColumn(name = "invoice_item_id"),
      joinColumns = @JoinColumn(name = "invoice_id"))
  private Set<InvoiceItem> invoiceItems = new HashSet<>();

  public abstract Integer processProductQuantity(InvoiceItem invoiceItem,
                                                 InventoryState inventoryState);

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public InvoiceStatus getStatus() {
    return status;
  }

  public void setStatus(InvoiceStatus status) {
    this.status = status;
  }

  public Set<InvoiceItem> getInvoiceItems() {
    return invoiceItems;
  }

  public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
    this.invoiceItems = invoiceItems;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Invoice invoice = (Invoice) o;
    return Objects.equal(id, invoice.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("status", status)
        .add("invoiceItems", invoiceItems)
        .toString();
  }
}
