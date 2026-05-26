package com.example.inventoryservice.event;

import lombok.Data;

@Data
public class InventoryFailedEvent {

    private String orderId;

    private String reason;
}
