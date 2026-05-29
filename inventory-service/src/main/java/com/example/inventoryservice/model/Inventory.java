package com.example.inventoryservice.model;

import lombok.Data;
import jakarta.persistence.*;


@Data
@Entity
@Table(name="inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productName;

    private int quantity;
}