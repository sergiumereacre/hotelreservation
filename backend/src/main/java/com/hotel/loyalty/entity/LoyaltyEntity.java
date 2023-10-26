package com.hotel.loyalty.entity;

import com.hotel.loyalty.service.Loyalty;
import com.hotel.accounts.entity.GuestAccountEntity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

@Entity
@Data
public class LoyaltyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "guest_account_id")
    private GuestAccountEntity account;

    @Enumerated(EnumType.STRING)
    private Loyalty loyalty = Loyalty.STANDARD;
}
