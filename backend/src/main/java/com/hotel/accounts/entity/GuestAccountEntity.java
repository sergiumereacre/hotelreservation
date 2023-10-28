package com.hotel.accounts.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Guest")
public class GuestAccountEntity extends AccountEntity {
    // Any additional fields specific to Guest
}


