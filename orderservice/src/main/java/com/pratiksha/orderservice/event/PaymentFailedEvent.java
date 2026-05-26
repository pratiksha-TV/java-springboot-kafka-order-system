package com.pratiksha.orderservice.event;

import lombok.Data;

@Data
public class PaymentFailedEvent {

    private String orderId;

    private String reason;
}
