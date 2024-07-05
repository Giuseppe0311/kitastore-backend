package com.ecommerce.order.mapper;

import com.ecommerce.order.models.OrderDetail;
import com.ecommerce.order.request.OrderDetailRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {

    OrderDetail toEntity(OrderDetailRequest request);
}
