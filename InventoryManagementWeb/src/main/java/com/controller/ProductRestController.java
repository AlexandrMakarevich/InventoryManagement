package com.controller;

import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProductRestController {

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;

    @RequestMapping(value = "/getAllProducts", method = RequestMethod.GET)
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }
}
