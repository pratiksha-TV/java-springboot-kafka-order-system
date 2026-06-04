package com.pratiksha.orderservice.repository;

import com.pratiksha.orderservice.model.OrderView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderViewRepository extends JpaRepository<OrderView,Long> {
}
