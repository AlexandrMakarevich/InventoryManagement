package com.controller;

import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.annotation.Resource;
import java.util.List;

@Controller
public class InvoiceController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @RequestMapping(value = "/invoice_page")
    public String createInvoice(Model model) {
        List<Product> productList = productDao.getAllProduct();
        model.addAttribute("products", productList);
        return "invoice_page";
    }
}
