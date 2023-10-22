package com.hotel.accounts.controller;

import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.accounts.service.HotelStaffAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/staff")
public class HotelStaffAccountController {

    @Autowired
    private HotelStaffAccountService service;

    @GetMapping
    public ResponseEntity<List<HotelStaffAccountEntity>> getAllStaff() {
        return ResponseEntity.ok(service.getAllStaff());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelStaffAccountEntity> getStaffById(@PathVariable Long id) {
        HotelStaffAccountEntity staff = service.getStaffById(id);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<HotelStaffAccountEntity> createStaff(@Valid @RequestBody HotelStaffAccountEntity staffAccount) {
        if (service.isAdmin(staffAccount.getId())) {
            return ResponseEntity.ok(service.saveStaff(staffAccount));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelStaffAccountEntity> updateStaff(@PathVariable Long id, @Valid @RequestBody HotelStaffAccountEntity staffAccount) {
        if (service.getStaffById(id) != null) {
            return ResponseEntity.ok(service.saveStaff(staffAccount));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        service.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}