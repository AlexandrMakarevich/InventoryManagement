package com.controller;

import com.google.common.base.MoreObjects;

import java.util.Map;

public class CreateInvoiceRequest {

    private String invoiceType;

    private Map<Integer, Integer> productQuantityMap;

    public Map<Integer, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Integer, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("invoiceType", invoiceType)
                .add("productQuantityMap", productQuantityMap)
                .toString();
    }
}
