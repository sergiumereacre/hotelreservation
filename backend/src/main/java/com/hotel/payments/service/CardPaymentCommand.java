package com.hotel.payments.service;

import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.entity.InvoiceEntity;

@Entity
public class CardPaymentCommand extends PaymentCommand {
    // Declare the required fields.
    private CardEntity card;

    @OneToOne
    private InvoiceEntity invoice;

    // Constructor with all the required fields.
    public CardPaymentCommand(CardEntity card, InvoiceEntity invoice) {
        this.card = card;
        this.invoice = invoice;
    }

    // Execute the command.
    @Override
    public void execute() {
        // Validation logic

        try {
            validateCardDetails(card);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        // Process card payment.
        // processPaymentWithCard(card);
        this.invoice.setPaymentType("Card");
        this.invoice.setPaid(true);
    }

    public void validateCardDetails(CardEntity cardEntity) {
        // Validate card number
        if (!isValidCardNumber(cardEntity.getCardNumber())) {
            throw new IllegalArgumentException("Invalid card number");
        }

        // Validate card holder name
        if (cardEntity.getCardHolderName() == null || cardEntity.getCardHolderName().isEmpty()) {
            throw new IllegalArgumentException("Card holder name is required");
        }

        // Validate expiry date
        if (!isValidExpiryDate(cardEntity.getExpiryDate())) {
            throw new IllegalArgumentException("Invalid expiry date");
        }

        // Validate CVV
        if (!isValidCvv(cardEntity.getCvv())) {
            throw new IllegalArgumentException("Invalid CVV");
        }
    }

    private boolean isValidCardNumber(int cardNumber) {
        // For simplicity, let's assume the card number is valid if it's a 16-digit
        // number.
        String cardNumberString = String.valueOf(cardNumber);
        return Pattern.matches("\\d{16}", cardNumberString);
    }

    private boolean isValidExpiryDate(String expiryDate) {
        // For simplicity, let's assume the expiry date is valid if it's in the format
        // MM/YY.
        return Pattern.matches("\\d{2}/\\d{2}", expiryDate);
    }

    private boolean isValidCvv(int cvv) {
        // For simplicity, let's assume the CVV is valid if it's a 3-digit number.
        String cvvString = String.valueOf(cvv);
        return Pattern.matches("\\d{3}", cvvString);
    }
}
