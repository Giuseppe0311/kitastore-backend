package com.ecommerce.products.repository;

import com.ecommerce.products.models.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository  extends MongoRepository<Products, String>{
}
