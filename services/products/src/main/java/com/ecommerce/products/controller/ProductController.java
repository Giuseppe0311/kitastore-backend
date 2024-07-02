package com.ecommerce.products.controller;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.dto.ProductMessageDTO;
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
    public ResponseEntity<ProductMessageDTO> create(@RequestBody @Valid ProductRequest productRequest){
        productService.create(productRequest);
        return ResponseEntity.ok(new ProductMessageDTO("Product created successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductMessageDTO> update(@PathVariable String id, @RequestBody @Valid ProductRequest productRequest){
        productService.update(id, productRequest);
        return ResponseEntity.ok(new ProductMessageDTO("Product updated successfully"));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductMessageDTO> delete(@PathVariable String id){
        productService.delete(id);
        return ResponseEntity.ok(new ProductMessageDTO("Product deleted successfully"));
    }
}
