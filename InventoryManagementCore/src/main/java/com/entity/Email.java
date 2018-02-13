package com.entity;

import com.constant.EmailStatus;
import com.google.common.base.MoreObjects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "email")
public class Email {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToOne
  @JoinColumn(name = "invoice_id")
  private Invoice invoice;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", columnDefinition = "ENUM('PENDING', 'SENT', 'ERROR')")
  private EmailStatus emailStatus = EmailStatus.PENDING;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Invoice getInvoice() {
    return invoice;
  }

  public void setInvoice(Invoice invoice) {
    this.invoice = invoice;
  }

  public EmailStatus getEmailStatus() {
    return emailStatus;
  }

  public void setEmailStatus(EmailStatus emailStatus) {
    this.emailStatus = emailStatus;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Email email = (Email) o;
    return id != null ? id.equals(email.id) : email.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("id", id)
        .add("invoice", invoice)
        .add("emailStatus", emailStatus)
        .toString();
  }
}
