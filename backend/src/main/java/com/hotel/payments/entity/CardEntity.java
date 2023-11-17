package com.hotel.payments.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
public class CardEntity {
    // Simple card details.

    // Max length of card number is 16.
    @Min(16)
    @Max(16)
    private int cardNumber;

    // Name on card can be max 50 characters.
    @Max(50)
    private String cardHolderName;

    // Expiry date is in format MM/YY.
    private String expiryDate;

    // CVV is 3 digits.
    @Min(3)
    @Max(3)
    private String cvv;
}
