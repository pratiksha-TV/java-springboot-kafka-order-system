package com.pratiksha.orderservice.consumer;

import com.pratiksha.orderservice.repository.OrderRepository;
import com.pratiksha.orderservice.repository.OrderViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProjection {
  private final OrderViewRepository orderViewRepository;
}
