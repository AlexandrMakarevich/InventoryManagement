package com.email;

import com.BaseTest;
import com.builder.InvoicePersistentBuilder;
import com.constant.EmailStatus;
import com.dao.EmailDao;
import com.entity.Email;
import com.entity.Invoice;
import org.junit.Assert;
import org.junit.Test;
import javax.annotation.Resource;

public class TestEmailDao extends BaseTest {

    @Resource(name = "emailDaoImpl")
    private EmailDao emailDao;

    @Resource(name = "invoicePersistentBuilder")
    private InvoicePersistentBuilder invoicePersistentBuilder;

    @Test
    public void testSaveEmailAndGetEmailById() {
        Invoice invoice = invoicePersistentBuilder.buildAndAddInvoice();
        Email expectedEmail = new Email();
        expectedEmail.setInvoice(invoice);
        emailDao.saveOrUpdateEmail(expectedEmail);
        Email actualEmail = emailDao.getEmailById(expectedEmail.getId());
        Assert.assertEquals("Actual result must be expected", expectedEmail, actualEmail);
    }

    @Test
    public void testUpdateEmailStatus() {
        Invoice invoice = invoicePersistentBuilder.buildAndAddInvoice();
        Email expectedEmail = new Email();
        expectedEmail.setInvoice(invoice);
        emailDao.saveOrUpdateEmail(expectedEmail);

        expectedEmail.setEmailStatus(EmailStatus.ERROR);
        emailDao.saveOrUpdateEmail(expectedEmail);

        Email actualEmail = emailDao.getEmailById(expectedEmail.getId());
        Assert.assertEquals("Actual emailStatus must be ERROR",
                actualEmail.getEmailStatus(), EmailStatus.ERROR);
    }
}
