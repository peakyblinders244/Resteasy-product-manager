package com.hcmus.manager.impl;

import com.hcmus.factory.ProductFactory;
import com.hcmus.manager.IProductConnector;
import com.hcmus.manager.base.BaseConnector;
import com.hcmus.model.Product;
import system.mongo.core.IDynamicObject;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.List;

public class ProductConnector extends BaseConnector implements IProductConnector {
    private MongoObjectConnector getMongoObjectConnectorProduct(){


        MongoObjectConnector tmp = getMongoObjectConnector("Product");

        return getMongoObjectConnector("Product");
    }

    public void save(Product product) {
        ProductFactory factory = new ProductFactory();
        IDynamicObject iDynamicObject = factory.createObject(product);
        getMongoObjectConnectorProduct().insert(iDynamicObject);
    }

    public Product getProduct(String id) {
        return (Product) getMongoObjectConnectorProduct().get(id,new ProductFactory());
    }

    public List<Product> getProductList() {
        return getMongoObjectConnectorProduct().listAll(new ProductFactory());
    }

    public List<Product> searchProductByName(String value) {
        String property = "name";
        return getMongoObjectConnectorProduct().getObjectMatchProperty(property,value,new ProductFactory());
    }

    public List<Product> searchProductByCatalog(String value) {
        String property = "catalogId";
        return getMongoObjectConnectorProduct().getObjectMatchProperty(property,value,new ProductFactory());
    }

    public List<Product> searchProductById(String value) {
        String property = "id";
        return getMongoObjectConnectorProduct().getObjectMatchProperty(property,value,new ProductFactory());
    }

    public void delete(String id) {
        getMongoObjectConnectorProduct().removeDocument(id);
    }

    public void update(Product product) {
        ProductFactory factory = new ProductFactory();
        IDynamicObject iDynamicObject = factory.createObject(product);
        getMongoObjectConnectorProduct().update(iDynamicObject);
    }
}
