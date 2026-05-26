package com.pratiksha.orderservice.producer;

import com.pratiksha.orderservice.event.OrderPlacedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrderEvent(OrderPlacedEvent event) {

        kafkaTemplate.send("order-topic", event);

        System.out.println("Order event sent to Kafka");
    }
}
