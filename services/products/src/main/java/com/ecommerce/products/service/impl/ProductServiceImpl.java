package com.ecommerce.products.service.impl;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.dto.ProductPurchaseDTO;
import com.ecommerce.products.exception.ProductInsufficientStock;
import com.ecommerce.products.exception.ProductNotFoundException;
import com.ecommerce.products.mapper.ProductMapper;
import com.ecommerce.products.models.Category;
import com.ecommerce.products.models.Products;
import com.ecommerce.products.repository.CategoryRepository;
import com.ecommerce.products.repository.ProductRepository;
import com.ecommerce.products.request.ProductPurchaseRequest;
import com.ecommerce.products.request.ProductRequest;
import com.ecommerce.products.service.ProductService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.findProductsByStatusTrue()
                .stream()
                .map(productMapper::toDTO).toList();
    }
    @Override
    public ProductDTO findById(String id) {
        return  productRepository.findById(id).map(productMapper::toDTO).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
    }

    @Override
    @Transactional
    public List<ProductPurchaseDTO> purchaseProduct(List<ProductPurchaseRequest> requests) {
        //verificar si el producto existe
        var idsProducts  = requests
                .stream()
                .map(ProductPurchaseRequest::getProductId).toList();
        var existProducts = productRepository.findProductsBy_idInAndStatusTrue(idsProducts);
        if (idsProducts.size() != existProducts.size()){
            throw new ProductNotFoundException("one or more products not found");
        }
        var purchasedProducts   = new ArrayList<ProductPurchaseDTO>();
        //validar el stock de los productos
        for(Products products : existProducts){
           var filteredProduct = requests.stream()
                   .filter(productRequest -> productRequest.getProductId().equals(products.get_id()))
                   .findFirst()
                   .orElseThrow(() -> new ProductNotFoundException("Product not found in request"));
            if (products.getStock() < filteredProduct.getQuantity()){
                throw new ProductInsufficientStock("insufficient stock for product: " + products.getName());
            }
            Integer newStockAvailable  = products.getStock() - filteredProduct.getQuantity();
            products.setStock(newStockAvailable);
            productRepository.save(products);
            purchasedProducts.add(productMapper.toPurchaseDTO(products, filteredProduct.getQuantity()));
        }
        return purchasedProducts;
    }

    @Override
    public void update(String id, ProductRequest productRequest) {
        Products products = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        productMapper.update(productRequest,products );
        productRepository.save(products);
    }
    @Override
    public void create(ProductRequest productRequest) {
        Products products = productMapper.toEntity(productRequest);
        productRepository.save(products);
    }

    @Override
    public void delete(String id) {
        Products products = productRepository.findById(id).orElseThrow(
                () -> new ProductNotFoundException("Product not found")
        );
        products.setStatus(false);
        productRepository.save(products);
    }

}
