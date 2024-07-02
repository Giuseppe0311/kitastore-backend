package com.ecommerce.products.mapper;

import com.ecommerce.products.dto.ProductDTO;
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
}
