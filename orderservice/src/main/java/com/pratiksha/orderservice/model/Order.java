package com.pratiksha.orderservice.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
@Data
public class Order {
    @Id
    private String id;

    private String productName;

    private double amount;

    private String status;
}
