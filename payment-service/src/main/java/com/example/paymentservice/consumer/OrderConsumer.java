package com.example.paymentservice.consumer;


import com.example.paymentservice.service.PaymentService;
import com.pratiksha.orderservice.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private final PaymentService paymentService;

    public OrderConsumer(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @KafkaListener(
            topics = "order-topic",
            groupId = "payment-group"
    )
    public void consume(OrderPlacedEvent event) {

        System.out.println("Payment processing started");

        paymentService.processPayment(event.getOrderId());
    }
}