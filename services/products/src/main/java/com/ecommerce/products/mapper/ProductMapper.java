package com.ecommerce.products.mapper;

import com.ecommerce.products.dto.ProductDTO;
import com.ecommerce.products.models.Products;
import com.ecommerce.products.request.ProductRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "name", source = "name")
    Products toEntity(ProductRequest productRequest);

    ProductDTO toDTO(Products products);
}
