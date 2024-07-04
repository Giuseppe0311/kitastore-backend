package com.ecommerce.products.mapper;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.dto.ProductPurchaseDTO;
import com.ecommerce.products.models.Products;
import com.ecommerce.products.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Products toEntity(ProductRequest productRequest);

    ProductDTO toDTO(Products products);


    void update(ProductRequest productRequest, @MappingTarget Products products);

    @Mapping(target = "id", source = "products._id")
    @Mapping(target = "name", source = "products.name")
    @Mapping(target = "description", source = "products.description")
    @Mapping(target = "price", source = "products.price")
    @Mapping(target = "quantity", source = "quantity")
    ProductPurchaseDTO toPurchaseDTO(Products products, Integer quantity);
}
