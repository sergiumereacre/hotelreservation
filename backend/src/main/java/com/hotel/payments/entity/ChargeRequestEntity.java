package com.hotel.payments.entity;

import javax.persistence.Entity;

import lombok.Data;

@Entity
@Data
public class ChargeRequestEntity {

    public enum Currency {
        EUR, USD;
    }
    
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;

}
