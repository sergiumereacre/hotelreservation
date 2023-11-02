package com.hotel.accounts.controller;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.accounts.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountService service;

    // STAFF ENDPOINTS

    @GetMapping("/staff")
    public ResponseEntity<List<AccountEntity>> getAllStaff() {
        return ResponseEntity.ok(service.getAllStaff());
    }

    @GetMapping("/staff/{id}")
    public ResponseEntity<AccountEntity> getStaffById(@PathVariable Long id) {
        AccountEntity staff = service.getStaffById(id);
        if (staff != null) {
            return ResponseEntity.ok(staff);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/staff")
    public ResponseEntity<AccountEntity> createStaff(@Valid @RequestBody HotelStaffAccountEntity staffData) {
        HotelStaffAccountEntity staffAccount = service.createStaffAccount();
        staffAccount.setName(staffData.getName());
        staffAccount.setEmail(staffData.getEmail());
        staffAccount.setPassword(staffData.getPassword());
        staffAccount.setRole(staffData.getRole());
        staffAccount.setStaff(true);  // Assuming all staff accounts have this as true
        return ResponseEntity.ok(service.saveAccount(staffAccount));
    }



    @PutMapping("/staff/{id}")
    public ResponseEntity<Map<String, Object>> updateStaff(@PathVariable Long id, @Valid @RequestBody HotelStaffAccountEntity staffAccount) {
        AccountEntity existingAccount = service.getStaffById(id);
        Map<String, Object> response = new HashMap<>();
        if (existingAccount != null) {
            existingAccount.setName(staffAccount.getName());
            existingAccount.setEmail(staffAccount.getEmail());
            existingAccount.setPassword(staffAccount.getPassword());
            existingAccount.setRole(staffAccount.getRole());

            AccountEntity updatedAccount = service.saveAccount(existingAccount);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Account not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/staff/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/staff/login")
    public ResponseEntity<?> authenticate(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<AccountEntity> staff = service.authenticateStaff(email, password);
        if (staff.isPresent()) {
            // login successful
            AccountEntity staffMember = staff.get();
            Map<String, Object> response = new HashMap<>();
            response.put("isAuthenticated", true);
            response.put("role", staffMember.getRole());
            response.put("id", staffMember.getId().toString());
            return ResponseEntity.ok(response);
        } else {
            // login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }


    // GUEST ENDPOINTS

    @GetMapping("/guests")
    public ResponseEntity<List<GuestAccountEntity>> getAllGuests() {
        return ResponseEntity.ok(service.getAllGuests());
    }

    @GetMapping("/guests/{id}")
    public ResponseEntity<GuestAccountEntity> getGuestById(@PathVariable Long id) {
        GuestAccountEntity guest = service.getGuestById(id);
        if (guest != null) {
            return ResponseEntity.ok(guest);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/guests/{id}")
    public ResponseEntity<Map<String, Object>> updateGuest(@PathVariable Long id, @Valid @RequestBody GuestAccountEntity guestAccount) {
       GuestAccountEntity existingAccount = service.getGuestById(id);
        Map<String, Object> response = new HashMap<>();
        if (existingAccount != null) {
            existingAccount.setEmail(guestAccount.getEmail());
            existingAccount.setPassword(guestAccount.getPassword());

            GuestAccountEntity updatedAccount = service.saveGuestAccount(existingAccount);
            return ResponseEntity.ok(response);
        } else {
            response.put("status", "error");
            response.put("message", "Account not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }



    @DeleteMapping("/guests/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        service.deleteAccount(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/guests/login")
    public ResponseEntity<?> guestAuthenticate(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        Optional<GuestAccountEntity> guest = service.authenticateGuest(email, password);
        if (guest.isPresent()) {
            // login successful
            GuestAccountEntity guestMember = guest.get();
            Map<String, Object> response = new HashMap<>();
            response.put("isAuthenticated", true);
            response.put("id", guestMember.getId().toString());
            return ResponseEntity.ok(response);
        } else {
            // login failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

    @PostMapping("/guests")
    public ResponseEntity<GuestAccountEntity> createGuest(@Valid @RequestBody GuestAccountEntity guestData) {
        GuestAccountEntity guestAccount = service.createGuestAccount();
        guestAccount.setName(guestData.getName());
        guestAccount.setEmail(guestData.getEmail());
        guestAccount.setPassword(guestData.getPassword());
        guestAccount.setNumStays(guestData.getNumStays());
        return ResponseEntity.ok(service.saveGuestAccount(guestAccount));
    }

}
