package com.pratiksha.orderservice.model;

import lombok.Data;

@Data
public class OrderStatusMessage {

    private Long orderId;

    private String status;

}
