package com.hotel.accounts.entity;

import lombok.Data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Guest")
@Data
public class GuestAccountEntity extends AccountEntity {

   private int numStays;

}


