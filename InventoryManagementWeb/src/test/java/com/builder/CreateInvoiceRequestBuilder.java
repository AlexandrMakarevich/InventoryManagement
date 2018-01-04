package com.builder;

import com.client.CreateInvoiceRequest;
import com.constant.InvoiceType;
import java.util.HashMap;
import java.util.Map;

public class CreateInvoiceRequestBuilder {

    private CreateInvoiceRequest createInvoiceRequest;

    public CreateInvoiceRequestBuilder() {
        init();
    }

    public void init() {
        createInvoiceRequest = new CreateInvoiceRequest();
        createInvoiceRequest.setInvoiceType(InvoiceType.IN);
        createInvoiceRequest.setProductQuantityMap(new HashMap<>());
    }

    public CreateInvoiceRequestBuilder withProductQuantityMap(Map<Integer, Integer> productQuantityMap) {
        createInvoiceRequest.setProductQuantityMap(productQuantityMap);
        return this;
    }

    public CreateInvoiceRequestBuilder withInvoiceType(InvoiceType invoiceType) {
        createInvoiceRequest.setInvoiceType(invoiceType);
        return this;
    }

    public CreateInvoiceRequest build() {
        return createInvoiceRequest;
    }
}
