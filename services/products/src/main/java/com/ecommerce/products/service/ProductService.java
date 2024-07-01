package com.ecommerce.products.service;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.request.ProductRequest;

import java.util.List;

public interface ProductService {
    void create(ProductRequest productRequest);
    List<ProductDTO> findAll();
    ProductDTO findById(String id);
}
