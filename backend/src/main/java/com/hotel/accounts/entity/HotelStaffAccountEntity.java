package com.hotel.accounts.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Staff")
public class HotelStaffAccountEntity extends AccountEntity {
   // private String role; // e.g., Receptionist, Housekeeping, Maintenance
    // Any additional fields specific to Staff
}
