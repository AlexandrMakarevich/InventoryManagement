package com.email;

import com.entity.Invoice;
import com.entity.InvoiceItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service(value = "invoiceEmailService")
public class InvoiceEmailService {

    @Resource(name = "emailSenderServiceImpl")
    private EmailSenderService emailSenderService;

    @Value("${managerEmail}")
    private String managerEmail;

    public void sendEmail(Invoice invoice) {
        emailSenderService.sendMessage(managerEmail,
                "New invoice",
                createEmailMessage(invoice).toString());
    }

    private StringBuilder createEmailMessage(Invoice invoice) {
        StringBuilder message = new StringBuilder();
        message.append("You have new invoice : ");
        for (InvoiceItem invoiceItem : invoice.getInvoiceItems()) {
            message.append(" product " + invoiceItem.getProduct().getProductName())
                    .append(" quantity " + invoiceItem.getProductQuantity())
                    .append(" ;");
        }
        return message;
    }
}
