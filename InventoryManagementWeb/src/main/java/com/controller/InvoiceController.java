package com.controller;

import com.constant.InvoiceType;
import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class InvoiceController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @GetMapping(value = "/createInvoice")
    public String initInvoice(Model model) {
        List<Product> productList = productDao.getAllProduct();
        model.addAttribute("products", productList);
        model.addAttribute("invoiceTypes", InvoiceType.values());
        model.addAttribute("createInvoiceRequest", new CreateInvoiceRequest());
        return "invoice_page";
    }

    @PostMapping(value = "/createInvoice")
    public String createInvoice(@ModelAttribute CreateInvoiceRequest createInvoice) {
        System.out.println(createInvoice);
        return "OK";
    }

}
