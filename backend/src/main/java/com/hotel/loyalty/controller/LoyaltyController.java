package com.hotel.loyalty.controller;

import com.hotel.accounts.entity.LoyaltyEntity;
import com.hotel.accounts.service.LoyaltyService;
import com.hotel.loyalty.service.LoyaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/loyalty")
public class LoyaltyController {

    @Autowired
    private LoyaltyService service;

    @GetMapping
    public ResponseEntity<List<LoyaltyService>> getAllLoyalties() {
        return ResponseEntity.ok(service.getAllLoyalties());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestAccountEntity> getLoyalty(@PathVariable Long id) {
        GuestAccountEntity guest = service.getGuestById(id);
        if (guest != null) {
            return ResponseEntity.ok(guest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<GuestAccountEntity> createGuest(@Valid @RequestBody GuestAccountEntity guestAccount) {
        return ResponseEntity.ok(service.saveGuest(guestAccount));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestAccountEntity> updateGuest(@PathVariable Long id, @Valid @RequestBody GuestAccountEntity guestAccount) {
        if (service.getGuestById(id) != null) {
            return ResponseEntity.ok(service.saveGuest(guestAccount));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        service.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
}
