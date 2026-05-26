package com.example.inventoryservice.consumer;

import com.example.inventoryservice.event.PaymentProcessedEvent;
import com.example.inventoryservice.service.InventoryService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class PaymentSuccessConsumer {
    private final InventoryService inventoryService;
    public PaymentSuccessConsumer(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @KafkaListener(
            topics = "payment-success-topic",
            groupId = "inventory-group"
    )

   public void consume(PaymentProcessedEvent event){
        inventoryService.updateInventory(
                event.getOrderId(), event.getProductName());

    }

}
