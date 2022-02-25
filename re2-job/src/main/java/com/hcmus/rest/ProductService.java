package com.hcmus.rest;


import com.hcmus.jwt.JwtTokenFilter;
import com.hcmus.manager.IProductConnector;
import com.hcmus.manager.impl.ProductConnector;
import com.hcmus.model.Product;
import org.apache.http.protocol.HTTP;
import org.jboss.resteasy.annotations.Body;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.xml.ws.RequestWrapper;
import java.util.List;

@Path("/products")
public class ProductService {
    private IProductConnector productConnector = new ProductConnector();
    //private JwtTokenFilter jwtTokenFilter = new JwtTokenFilter();

    @GET
    @Produces("application/json")
    public List<Product>  findAll(){

        List<Product> productList = null;
        try{

            productList = productConnector.getProductList();
        }catch (Exception exception){

        }
        return productList;
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createProduct(Product product){
        try
        {
            productConnector.save(product);
            return Response.ok().status(Response.Status.CREATED).build();
        }catch (Exception exception){
            return Response.notModified().build();
        }

    }

    @PUT
    @Path("/{ProducId}")
    @Produces("application/json")
    public Response updateOne(@PathParam("ProducId") String productId, Product productUpdate){
        try
        {
            Product product = productConnector.getProduct(productId);
            product.setCatalogId(productUpdate.getCatalogId());
            product.setName(productUpdate.getName());
            product.setImage(productUpdate.getImage());
            product.setPrice(productUpdate.getPrice());
            productConnector.update(product);
            return Response.ok().status(Response.Status.CREATED).build();
        }catch (Exception exception){
            return Response.notModified().build();
        }
    }

    @DELETE
    @Path("/{ProducId}")
    @Produces("application/json")
    public Response deleteOne(@PathParam("ProducId") String productId){
        try
        {
            productConnector.delete(productId);
            return Response.ok().status(Response.Status.CREATED).build();
        }catch (Exception exception){
            return Response.notModified().build();
        }
    }

    @GET
    @Path("/{ProducId}")
    @Produces("application/json")
    public Product findOne(@PathParam("ProducId") String productId){
        Product product = null;
        try{
            product = productConnector.searchProductById(productId).get(0);
        }catch (Exception exception){

        }
        return product;
    }


}
