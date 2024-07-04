package com.ecommerce.order.repository;

import com.ecommerce.order.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Orders,Integer> {
}
