package com.ecommerce.order.mapper;

import com.ecommerce.order.dto.OrdersDto;
import com.ecommerce.order.models.Orders;
import com.ecommerce.order.request.OrderRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring",uses = {OrderDetailMapper.class})
public interface OrdersMapper {
    OrdersDto toDto(Orders orders);
    Orders toEntity(OrderRequest orderRequest);
    void update (OrderRequest orderRequest , @MappingTarget Orders orders);

}
