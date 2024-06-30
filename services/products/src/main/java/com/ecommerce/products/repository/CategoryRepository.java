package com.ecommerce.products.repository;

import com.ecommerce.products.models.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String>{
}
