package com.pratiksha.orderservice.consumer;

import com.pratiksha.orderservice.event.PaymentFailedEvent;
import com.pratiksha.orderservice.model.Order;
import com.pratiksha.orderservice.repository.OrderRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentFailedConsumer {

    private final OrderRepository orderRepository;

    public PaymentFailedConsumer(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @KafkaListener(
            topics = "payment-failed-topic",
            groupId = "order-group"
    )
    public void consume(PaymentFailedEvent event) {

        Order order =
                orderRepository.findById(event.getOrderId())
                        .orElseThrow();

        order.setStatus("CANCELLED");

        orderRepository.save(order);

        System.out.println("Order cancelled due to payment failure");
    }
}
