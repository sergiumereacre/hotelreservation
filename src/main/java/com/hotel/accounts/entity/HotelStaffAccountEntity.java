package com.hotel.accounts.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class HotelStaffAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password; // probs should encrypt
    private String role; // e.g., Receptionist, Housekeeping, Maintenance
    // maybe more fields
}