package com.email;

import com.constant.EmailStatus;
import com.dao.EmailDaoImpl;
import com.entity.Email;
import com.entity.Invoice;
import com.entity.InvoiceIn;
import com.google.common.collect.ImmutableList;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TestEmailService {

    private EmailService emailService;
    private EmailDaoImpl emailDao = Mockito.mock(EmailDaoImpl.class);
    private InvoiceEmailSender invoiceEmailSender = Mockito.mock(InvoiceEmailSender.class);

    @Before
    public void init() {
        emailService = new EmailService(emailDao, invoiceEmailSender);
    }

    @Test
    public void testProcessPendingEmail() {
        Email expectedEmail = new Email();
        Invoice invoice = new InvoiceIn();
        expectedEmail.setInvoice(invoice);

        Mockito.when(emailDao.getAllPendingEmail()).thenReturn(ImmutableList.of(expectedEmail));

        emailService.processPendingEmail();

        Mockito.verify(invoiceEmailSender, Mockito.times(1)).sendEmail(invoice);
        Mockito.verify(emailDao, Mockito.times(1)).saveOrUpdateEmail(expectedEmail);
    }

    @Test
    public void testProcessPendingEmailWhenErrorHappend() {
        Email expectedEmail = new Email();
        Invoice invoice = new InvoiceIn();
        expectedEmail.setInvoice(invoice);

        Mockito.when(emailDao.getAllPendingEmail()).thenReturn(ImmutableList.of(expectedEmail));
        Mockito.doThrow(IllegalArgumentException.class).when(invoiceEmailSender).sendEmail(invoice);

        emailService.processPendingEmail();

        Mockito.verify(emailDao, Mockito.times(1))
                .saveOrUpdateEmail(expectedEmail);
        Assert.assertEquals("Actual result must be expected",
                expectedEmail.getEmailStatus(), EmailStatus.ERROR);
    }
}
