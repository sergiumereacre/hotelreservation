package com.hotel.payments.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ChargeRequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int chargeRequestId;

    public enum Currency {
        EUR, USD;
    }
    
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;

}
