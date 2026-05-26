package com.example.inventoryservice.event;

import lombok.Data;

@Data
public class InventoryUpdatedEvent {

    private String orderId;

    private String status;
}
