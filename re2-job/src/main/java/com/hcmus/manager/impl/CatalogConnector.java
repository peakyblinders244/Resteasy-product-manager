package com.hcmus.manager.impl;

import com.hcmus.factory.CatalogFactory;
import com.hcmus.manager.ICatalogConnector;
import com.hcmus.manager.base.BaseConnector;
import com.hcmus.model.Catalog;

import system.mongo.core.IDynamicObject;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.List;

public class CatalogConnector extends BaseConnector implements ICatalogConnector {
    private MongoObjectConnector getMongoObjectConnectorCatalog(){


        MongoObjectConnector tmp = getMongoObjectConnector("Catalog");

        return getMongoObjectConnector("Catalog");
    }

    public void save(Catalog catalog) {
        CatalogFactory factory = new CatalogFactory();
        IDynamicObject iDynamicObject =factory.createObject(catalog);
        getMongoObjectConnectorCatalog().insert(iDynamicObject);
    }

    public Catalog getCatalog(String id) {
        return (Catalog) getMongoObjectConnectorCatalog().get(id,new CatalogFactory());
    }

    public List<Catalog> getCatalogList() {
        return getMongoObjectConnectorCatalog().listAll(new CatalogFactory());
    }

    public List<Catalog> searchCatalogByName(String searchValue) {
        return null;
    }

    public void delete(String id) {
        getMongoObjectConnectorCatalog().removeDocument(id);
    }

    public void update(Catalog category) {
        CatalogFactory factory = new CatalogFactory();
        IDynamicObject iDynamicObject = factory.createObject(category);
        getMongoObjectConnectorCatalog().update(iDynamicObject);
    }
}
