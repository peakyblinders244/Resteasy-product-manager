package com.hcmus.rest;


import com.hcmus.manager.IProductConnector;
import com.hcmus.manager.impl.ProductConnector;
import com.hcmus.model.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/products")
public class ProductService {
    private IProductConnector productConnector;
    @GET
    public Response getAll(){
        productConnector = new ProductConnector();
        List<Product> tmp = productConnector.getProductList();
        return Response.status(200).entity(tmp.get(1)).build();
    }


}
