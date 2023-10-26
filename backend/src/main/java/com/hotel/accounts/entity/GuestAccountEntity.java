package com.hotel.accounts.entity;

import lombok.Data;
import com.hotel.loyalty.interfaces.ILoyaltyObserver;
import com.hotel.loyalty.service.Loyalty;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Guest")
@Data
public class GuestAccountEntity extends AccountEntity {

   private int numStays;
   private boolean isGuest = true;
}


