package com.dao;

import com.entity.Product;
import java.util.List;

public interface ProductDao {

  int saveProduct(Product product);

  Product getProductById(int id);

  List<Product> getAllProduct();
}
