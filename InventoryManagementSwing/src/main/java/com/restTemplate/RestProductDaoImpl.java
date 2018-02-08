package com.restTemplate;

import com.dao.ProductDao;
import com.entity.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import java.io.IOException;
import java.util.List;

@Repository("restProductDaoImpl")
public class RestProductDaoImpl extends BaseRestTemplate implements ProductDao {

    @Override
    public int saveProduct(Product product) {
        return 0;
    }

    @Override
    public Product getProductById(int id) {
        return null;
    }

    @Override
    public List<Product> getAllProduct() {
        ObjectMapper objectMapper = new ObjectMapper();
        String productList = restTemplate.getForObject(baseUrl + "/getAllProducts", String.class);
        try {
            return objectMapper.readValue(productList, new TypeReference<List<Product>>() {});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
