package com.example.inventoryservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name = "processed_events")
@RequiredArgsConstructor
public class ProcessedEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String eventId;


    public ProcessedEvent(String eventId) {
    }
}
