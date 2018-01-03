package com.controller;

import com.client.InvoiceData;
import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class InvoiceController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @RequestMapping(value = "/invoice_page"+"12 \" 3")
    public String createInvoice(Model model) {
        List<Product> productList = productDao.getAllProduct();
        model.addAttribute("products", productList);
//        Map<Integer, Integer> invoiceData = new HashMap<>();
        model.addAttribute("data", new InvoiceData());
        return "invoice_page";
    }
    @RequestMapping(value = "/invoice_page", method = RequestMethod.POST)
    public String processMap(@ModelAttribute(value = "data") InvoiceData invoiceData) {
        System.out.println(invoiceData);
        return "test";
    }
}
