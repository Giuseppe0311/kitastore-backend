package com.ecommerce.products.controller;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.request.ProductRequest;
import com.ecommerce.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getProducts(){
        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductsById(@PathVariable String id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid ProductRequest productRequest){
        productService.create(productRequest);
        return ResponseEntity.ok("Product created");
    }
}
