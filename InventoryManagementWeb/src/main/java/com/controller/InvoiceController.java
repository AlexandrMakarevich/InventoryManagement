package com.controller;

import com.client.CreateInvoiceRequest;
import com.client.InvoiceRequestBuilder;
import com.constant.InvoiceType;
import com.dao.InvoiceDao;
import com.dao.ProductDao;
import com.entity.Invoice;
import com.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.annotation.Resource;
import java.util.List;

@Controller("invoiceController")
public class InvoiceController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @Resource(name = "invoiceDaoImpl")
    private InvoiceDao invoiceDao;

    @Resource(name = "invoiceRequestBuilder")
    private InvoiceRequestBuilder invoiceRequestBuilder;

    @GetMapping(value = "/invoice_page")
    public String initInvoice(Model model) {
        List<Product> productList = productDao.getAllProduct();
        model.addAttribute("products", productList);
        model.addAttribute("invoiceTypes", InvoiceType.values());
        model.addAttribute("createInvoiceRequest", new CreateInvoiceRequest());
        return "invoice_page";
    }

    @PostMapping(value = "/invoice_page")
    public String createInvoice(@ModelAttribute CreateInvoiceRequest createInvoice) {
        if (createInvoice == null || createInvoice.getProductQuantityMap().isEmpty()) {
            throw new IllegalStateException("CreateInvoiceRequest must be initialize");
        }
        Invoice invoice = invoiceRequestBuilder.buildInvoice(createInvoice);
        invoiceDao.saveOrUpdateInvoice(invoice);
        return "invoice_creation_complete";
    }
}
