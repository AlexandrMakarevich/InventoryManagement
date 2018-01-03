package com.client;

public class InvoiceData {

    private Integer productId;
    private Integer productQuantity;

    public InvoiceData() {
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "InvoiceData{" +
                "productId=" + productId +
                ", productQuantity=" + productQuantity +
                '}';
    }
}
