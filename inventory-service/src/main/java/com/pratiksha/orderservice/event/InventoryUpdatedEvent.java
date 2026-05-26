package com.pratiksha.orderservice.event;

import lombok.Data;

@Data
public class InventoryUpdatedEvent {

    private String orderId;

    private String status;
}
