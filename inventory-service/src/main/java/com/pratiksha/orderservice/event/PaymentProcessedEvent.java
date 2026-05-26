package com.pratiksha.orderservice.event;
import lombok.Data;

@Data
public class PaymentProcessedEvent {

    private String orderId;

    private String status;

    private String productName;
}