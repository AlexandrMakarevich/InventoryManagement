package com.email;

import com.constant.EmailStatus;
import com.dao.EmailDao;
import com.entity.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

  private EmailDao emailDao;
  private InvoiceEmailSender invoiceEmailSender;
  private static final Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  @Autowired
  public EmailService(EmailDao emailDao, InvoiceEmailSender invoiceEmailSender) {
    this.emailDao = emailDao;
    this.invoiceEmailSender = invoiceEmailSender;
  }

  /**
   * This is method processed email with status PENDING,
   * if all is correct status will be changed on SENT,
   * if some thing wrong happen, status will be ERROR.
   */
  public void processPendingEmail() {
    for (Email email : emailDao.getAllPendingEmail()) {
      try {
        invoiceEmailSender.sendEmail(email.getInvoice());
        email.setEmailStatus(EmailStatus.SENT);
      } catch (Exception e) {
        email.setEmailStatus(EmailStatus.ERROR);
        LOGGER.warn("Message not sending to manager because " + e.getMessage());
      }
      emailDao.saveOrUpdateEmail(email);
    }
  }
}
