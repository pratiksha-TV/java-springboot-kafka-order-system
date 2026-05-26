package com.example.analyticsservice.consumer;

import com.pratiksha.orderservice.event.OrderPlacedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsConsumer {

    @KafkaListener(
            topics = "order-topic",
            groupId = "analytics-group"
    )
    public void consume(OrderPlacedEvent event) {

        System.out.println("Analytics Event Received");

        System.out.println("Product: " + event.getProductName());

        System.out.println("Revenue: " + event.getAmount());
    }
}
