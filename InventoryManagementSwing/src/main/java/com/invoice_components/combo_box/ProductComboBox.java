package com.invoice_components.combo_box;

import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.util.List;
import static com.invoice_components.combo_box.ProductComboBox.PRODUCT_COMBO_BOX_BEAN;

@Component(PRODUCT_COMBO_BOX_BEAN)
public class ProductComboBox extends JComboBox<String> {

    public static final String PRODUCT_COMBO_BOX_BEAN = "productComboBox";

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;
    private List<Product> products;

    @PostConstruct
    public void init() {
        setSize(200, 50);
        addData();
    }

    public void addData() {
        products = productDao.getAllProduct();
        for (Product product : products) {
            addItem(product.getProductName());
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}
