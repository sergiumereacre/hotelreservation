package com.hotel.loyalty.controller;

import com.hotel.loyalty.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService service;

    @GetMapping("/{id}")
    public ResponseEntity<String> getLoyalty(@PathVariable Long id) {
        String loyaltyType = service.getLoyaltyType(id);
        if (loyaltyType != null) {
            return ResponseEntity.ok(loyaltyType);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
