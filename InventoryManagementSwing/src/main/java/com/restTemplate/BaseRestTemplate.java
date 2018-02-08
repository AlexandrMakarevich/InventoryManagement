package com.restTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@PropertySource("classpath:inventory_management_swing.properties")
public class BaseRestTemplate {

    protected RestTemplate restTemplate = new RestTemplate();

    protected String baseUrl;

    @Value("${baseUrl}")
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
