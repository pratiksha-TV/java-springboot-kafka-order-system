package com.pratiksha.orderservice.service;

import com.pratiksha.orderservice.event.OrderPlacedEvent;
import com.pratiksha.orderservice.model.Order;
import com.pratiksha.orderservice.producer.OrderProducer;
import com.pratiksha.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;

    public OrderService(OrderRepository orderRepository,
                        OrderProducer orderProducer) {

        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
    }

    public Order createOrder(Order order) {

        order.setStatus("CREATED");

        Order savedOrder = orderRepository.save(order);

        OrderPlacedEvent event = new OrderPlacedEvent();

        event.setOrderId(savedOrder.getId());
        event.setProductName(savedOrder.getProductName());
        event.setAmount(savedOrder.getAmount());

        orderProducer.sendOrderEvent(event);

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
