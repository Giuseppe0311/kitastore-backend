package com.ecommerce.products.controller;

import com.ecommerce.products.request.ProductRequest;
import com.ecommerce.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ProductRequest productRequest){
        productService.create(productRequest);
        return ResponseEntity.ok("Product created");
    }
}
