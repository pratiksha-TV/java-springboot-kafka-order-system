package com.pratiksha.orderservice.repository;

import com.pratiksha.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {

}
