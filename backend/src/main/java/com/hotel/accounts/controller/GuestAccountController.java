package com.hotel.accounts.controller;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.service.GuestAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/guests")
public class GuestAccountController {

    @Autowired
    private GuestAccountService service;

    @GetMapping
    public ResponseEntity<List<GuestAccountEntity>> getAllGuests() {
        return ResponseEntity.ok(service.getAllGuests());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestAccountEntity> getGuestById(@PathVariable Long id) {
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

