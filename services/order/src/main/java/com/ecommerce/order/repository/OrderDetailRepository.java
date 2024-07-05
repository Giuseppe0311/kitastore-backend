package com.ecommerce.order.repository;

import com.ecommerce.order.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer>{
}
