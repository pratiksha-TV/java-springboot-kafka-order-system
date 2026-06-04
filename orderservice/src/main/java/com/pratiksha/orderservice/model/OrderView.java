package com.pratiksha.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="order_view")
public class OrderView {
    @Id
    private Long orderId;
    private String status;


}
