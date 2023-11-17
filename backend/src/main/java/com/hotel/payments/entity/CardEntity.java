package com.hotel.payments.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.Data;

@Entity
@Data
public class CardEntity {
    // Card number is 16 digits.
    private int cardNumber;

    // Name on card.
    private String cardHolderName;

    // Expiry date is in format MM/YY.
    private String expiryDate;

    // CVV is 3 digits.
    @Min(100)
    @Max(999)
    private int cvv;
}
