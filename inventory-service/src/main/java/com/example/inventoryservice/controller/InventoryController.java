package com.example.inventoryservice.controller;


import com.example.inventoryservice.model.Inventory;
import com.example.inventoryservice.repository.InventoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(
            InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    @PostMapping
    public Inventory addInventory(
            @RequestBody Inventory inventory) {

        return inventoryRepository.save(inventory);
    }

    @GetMapping
    public List<Inventory> getInventory() {

        return inventoryRepository.findAll();
    }
}
