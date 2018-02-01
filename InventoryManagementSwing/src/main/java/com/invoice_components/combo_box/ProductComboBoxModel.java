package com.invoice_components.combo_box;

import com.dao.ProductDao;
import com.entity.Product;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import static com.invoice_components.combo_box.ProductComboBoxModel.PRODUCT_COMBO_BOX_MODEL_BEAN;

@Component(PRODUCT_COMBO_BOX_MODEL_BEAN)
public class ProductComboBoxModel extends AbstractListModel implements ComboBoxModel{

    public static final String PRODUCT_COMBO_BOX_MODEL_BEAN = "productComboBoxModel";

    @Resource(name = "productDaoImpl")
    private ProductDao productDao;
    private List<ItemProduct> itemProducts;
    private ItemProduct selectedProduct;

    @PostConstruct
    public void init() {
        itemProducts = new ArrayList<>();
        for (Product product : productDao.getAllProduct()) {
            itemProducts.add(new ItemProduct(product));
        }
    }

    @Override
    public int getSize() {
        return itemProducts.size();
    }

    @Override
    public Object getElementAt(int index) {
        return itemProducts.get(index);
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selectedProduct = (ItemProduct) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selectedProduct;
    }
}
