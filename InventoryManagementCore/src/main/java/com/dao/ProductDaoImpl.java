package com.dao;

import com.entity.Product;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("productDaoImpl")
public class ProductDaoImpl extends BaseDao implements ProductDao {

    @Override
    public int saveProduct(Product product) {
        getSession().save(product);
        return product.getId();
    }

    @Override
    public Product getProductById(int id) {
        return getSession().get(Product.class, id);
    }

    @Override
    public List<Product> getAllProduct() {
        String sql = "select * from product";
        Query<Product> query = getSession().createNativeQuery(sql, Product.class);
        return  query.list();
    }

    @Override
    public Product getProductByName(String productName) {
        String sql = "select * from product where name = :param_name";
        NativeQuery<Product> nativeQuery = getSession().createNativeQuery(sql, Product.class);
        nativeQuery.setParameter("param_name", productName);
        return nativeQuery.getSingleResult();
    }
}
