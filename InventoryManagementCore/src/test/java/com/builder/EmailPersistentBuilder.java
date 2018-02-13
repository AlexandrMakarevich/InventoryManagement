package com.builder;

import com.dao.EmailDao;
import com.entity.Email;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository("emailPersistentBuilder")
public class EmailPersistentBuilder {

    @Resource(name = "emailDaoImpl")
    private EmailDao emailDao;

    @Resource(name = "invoicePersistentBuilder")
    private InvoicePersistentBuilder invoicePersistentBuilder;

    public Email buildAndAddEmail() {
        Email email = new Email();
        email.setInvoice(invoicePersistentBuilder.buildAndAddInvoice());
        emailDao.saveOrUpdateEmail(email);
        return email;
    }
}
