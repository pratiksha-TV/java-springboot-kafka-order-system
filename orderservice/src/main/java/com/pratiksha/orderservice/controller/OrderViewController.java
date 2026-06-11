package com.pratiksha.orderservice.controller;

import com.pratiksha.orderservice.model.OrderView;
import com.pratiksha.orderservice.repository.OrderViewRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders/view")
public class OrderViewController {

    private final OrderViewRepository orderViewRepository;

    public OrderViewController(
            OrderViewRepository orderViewRepository) {

        this.orderViewRepository = orderViewRepository;
    }

    @GetMapping("/{id}")
    public OrderView getOrderView(
            @PathVariable Long id) {

        return orderViewRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order not found"));
    }
}