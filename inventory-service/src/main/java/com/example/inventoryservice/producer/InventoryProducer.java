package com.example.inventoryservice.producer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public InventoryProducer(
            KafkaTemplate<String, Object> kafkaTemplate) {

        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendInventoryUpdated(Object event) {

        kafkaTemplate.send(
                "inventory-success-topic",
                event
        );
    }

    public void sendInventoryFailed(Object event) {

        kafkaTemplate.send(
                "inventory-failed-topic",
                event
        );
    }
}
