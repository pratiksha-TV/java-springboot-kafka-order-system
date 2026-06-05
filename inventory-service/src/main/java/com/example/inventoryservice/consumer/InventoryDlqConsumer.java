package com.example.inventoryservice.consumer;

import com.pratiksha.orderservice.event.PaymentProcessedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class InventoryDlqConsumer {

    @KafkaListener(
            topics = "inventory-dlq-topic"
    )
    public void consumeDlq(
            PaymentProcessedEvent event) {

        System.out.println(
                "DLQ EVENT RECEIVED : "
                        + event
        );
    }
}
