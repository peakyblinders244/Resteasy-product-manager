package com.hcmus.app;

import com.hcmus.jwt.JwtTokenFilter;
import com.hcmus.rest.ProductService;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ProductApplication extends Application {
    private Set<Object> singletons = new HashSet<Object>();
    public ProductApplication(){
        singletons.add(new JwtTokenFilter());
        singletons.add(new ProductService());

    }
    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }
}
