package com.client;

import com.constant.InvoiceType;
import com.google.common.base.MoreObjects;

import java.util.Map;

public class CreateInvoiceRequest {

    private InvoiceType invoiceType;

    private Map<Integer, Integer> productQuantityMap;

    public Map<Integer, Integer> getProductQuantityMap() {
        return productQuantityMap;
    }

    public void setProductQuantityMap(Map<Integer, Integer> productQuantityMap) {
        this.productQuantityMap = productQuantityMap;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("invoiceType", invoiceType)
                .add("productQuantityMap", productQuantityMap)
                .toString();
    }
}
