package com.hotel.loyalty.entity;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.loyalty.service.Loyalty;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.EnumType;

@Entity
@Data
public class LoyaltyEntity {
    // Loyalty id is the same as the guest account id.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "guest_account_id")
    private GuestAccountEntity guestAccount;

    // The loyalty type of the guest.
    @Enumerated(EnumType.STRING)
    private Loyalty type = Loyalty.STANDARD;
}
