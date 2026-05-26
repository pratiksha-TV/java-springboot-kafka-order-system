package com.example.notification_service.consumer;
import com.pratiksha.orderservice.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @KafkaListener(
            topics = "order-topic",
            groupId = "notification-group"
    )
    public void consume(OrderPlacedEvent event) {

        System.out.println("Order Received");

        System.out.println(event);

        System.out.println("Thank you for your order");
    }
}