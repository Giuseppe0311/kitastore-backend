package com.ecommerce.products.service;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.dto.ProductPurchaseDTO;
import com.ecommerce.products.request.ProductPurchaseRequest;
import com.ecommerce.products.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findAll();
    ProductDTO findById(String id);
    List<ProductPurchaseDTO> purchaseProduct(List<ProductPurchaseRequest> requests);
    void create(ProductRequest productRequest);
    void update(String id, ProductRequest productRequest);
    void delete(String id);

}
