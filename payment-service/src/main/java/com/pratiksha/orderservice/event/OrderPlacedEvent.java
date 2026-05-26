package com.pratiksha.orderservice.event;

import lombok.Data;

@Data
public class OrderPlacedEvent {

    private String orderId;

    private String productName;

    private double amount;
}
