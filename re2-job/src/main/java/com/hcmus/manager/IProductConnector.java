package com.hcmus.manager;

import com.hcmus.model.Product;

import java.util.List;

public interface IProductConnector {
    void save(Product product);

    Product getProduct(String id);

    List<Product> getProductList();

    List<Product> searchProductByName(String value);

    List<Product> searchProductByCatalog(String value);

    List<Product> searchProductById(String value);

    void delete(String id);

    void update(Product product);
}
