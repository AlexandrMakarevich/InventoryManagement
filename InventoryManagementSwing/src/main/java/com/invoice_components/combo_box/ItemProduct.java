package com.invoice_components.combo_box;

import com.entity.Product;

public class ItemProduct {

    private Product product;

    public ItemProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public String toString() {
        return product.getProductName();
    }
}
