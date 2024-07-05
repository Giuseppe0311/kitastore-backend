package com.ecommerce.products.repository;

import com.ecommerce.products.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository  extends MongoRepository<Products, String>{
    Products findProductsBy_id(String id);
    List<Products> findProductsByStatusTrue();
    List<Products> findProductsBy_idInAndStatusTrue(List<String> ids);
}
