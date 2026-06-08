package com.example.inventoryservice.consumer;

import com.pratiksha.orderservice.event.PaymentProcessedEvent;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class PaymentSuccessConsumer {
    private final InventoryService inventoryService;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentSuccessConsumer(InventoryService inventoryService, KafkaTemplate<String, Object> kafkaTemplate) {
        this.inventoryService = inventoryService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaListener(
            topics = "payment-success-topic",
            groupId = "inventory-group"
    )

   public void consume(PaymentProcessedEvent event){

        System.out.println(
                "Processing Inventory..."
        );

        System.out.println(
                "Retry "+event
        );
        throw new RuntimeException(
                "Inventory Failed"
        );

    }

}
