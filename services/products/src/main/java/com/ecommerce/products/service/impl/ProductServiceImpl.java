package com.ecommerce.products.service.impl;

import com.ecommerce.products.mapper.ProductMapper;
import com.ecommerce.products.models.Category;
import com.ecommerce.products.models.Products;
import com.ecommerce.products.repository.CategoryRepository;
import com.ecommerce.products.repository.ProductRepository;
import com.ecommerce.products.request.ProductRequest;
import com.ecommerce.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    @Override
    public void create(ProductRequest productRequest) {
        Products products = productMapper.toEntity(productRequest);
        productRepository.save(products);

    }
}
