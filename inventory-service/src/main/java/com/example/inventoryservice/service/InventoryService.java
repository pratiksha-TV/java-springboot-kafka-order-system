package com.example.inventoryservice.service;


import com.example.inventoryservice.event.InventoryFailedEvent;
import com.example.inventoryservice.event.InventoryUpdatedEvent;
import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.producer.InventoryProducer;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    private final InventoryProducer inventoryProducer;

    public InventoryService(
            InventoryRepository inventoryRepository,
            InventoryProducer inventoryProducer) {

        this.inventoryRepository = inventoryRepository;
        this.inventoryProducer = inventoryProducer;
    }

    public void updateInventory(
            String orderId,
            String productName) {

        Inventory inventory =
                inventoryRepository
                        .findByProductName(productName)
                        .orElse(null);

        if(inventory == null ||
                inventory.getQuantity() <= 0) {

            InventoryFailedEvent failedEvent =
                    new InventoryFailedEvent();

            failedEvent.setOrderId(orderId);
            failedEvent.setReason("Out of stock");

            inventoryProducer
                    .sendInventoryFailed(failedEvent);

            return;
        }

        inventory.setQuantity(
                inventory.getQuantity() - 1
        );

        inventoryRepository.save(inventory);

        InventoryUpdatedEvent successEvent =
                new InventoryUpdatedEvent();

        successEvent.setOrderId(orderId);
        successEvent.setStatus("UPDATED");

        inventoryProducer
                .sendInventoryUpdated(successEvent);

        System.out.println(
                "Inventory updated successfully"
        );
    }
}