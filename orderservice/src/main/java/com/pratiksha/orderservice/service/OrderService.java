package com.pratiksha.orderservice.service;

import com.pratiksha.orderservice.event.OrderPlacedEvent;
import com.pratiksha.orderservice.model.EventStore;
import com.pratiksha.orderservice.model.Order;
import com.pratiksha.orderservice.producer.OrderProducer;
import com.pratiksha.orderservice.repository.EventStoreRepository;
import com.pratiksha.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderProducer orderProducer;
    private final EventStoreRepository eventStoreRepository;

    public OrderService(OrderRepository orderRepository,
                        OrderProducer orderProducer, EventStoreRepository eventStoreRepository) {

        this.orderRepository = orderRepository;
        this.orderProducer = orderProducer;
        this.eventStoreRepository = eventStoreRepository;
    }

    public Order createOrder(Order order) {
        order.setStatus("CREATED");

        Order savedOrder = orderRepository.save(order);
        EventStore eventStore = new EventStore();

        eventStore.setOrderId(order.getId());

        eventStore.setEventType("ORDER_CREATED");

        eventStore.setCreatedAt(LocalDateTime.now());

        eventStoreRepository.save(eventStore);

        OrderPlacedEvent event = new OrderPlacedEvent();

        event.setOrderId(String.valueOf(savedOrder.getId()));
        event.setProductName(savedOrder.getProductName());
        event.setAmount(savedOrder.getAmount());

        orderProducer.sendOrderEvent(event);

        return savedOrder;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
