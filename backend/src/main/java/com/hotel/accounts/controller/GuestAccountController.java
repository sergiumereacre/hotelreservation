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

    // Mapping that returns the numStays of a guest.
    @GetMapping("/{id}/numStays")
    public ResponseEntity<Integer> getNumStays(@PathVariable Long id) {
        GuestAccountEntity guest = service.getGuestById(id);
        if (guest != null) {
            return ResponseEntity.ok(guest.getNumStays());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Put method that increments the numStays of a guest by 1.
    @PutMapping("/{id}/numStays")
    public ResponseEntity<GuestAccountEntity> updateNumStays(@PathVariable Long id) {
        GuestAccountEntity guest = service.getGuestById(id);
        if (guest != null) {
            guest.setNumStays(guest.getNumStays() + 1);
            return ResponseEntity.ok(service.saveGuest(guest));
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

