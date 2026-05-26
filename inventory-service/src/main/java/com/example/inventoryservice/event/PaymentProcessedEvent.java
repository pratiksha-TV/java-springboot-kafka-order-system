package com.example.inventoryservice.event;
import lombok.Data;

@Data
public class PaymentProcessedEvent {

    private String orderId;

    private String status;
}