package com.pratiksha.orderservice.event;

import lombok.Data;

@Data
public class InventoryFailedEvent {

    private String orderId;

    private String reason;
}
