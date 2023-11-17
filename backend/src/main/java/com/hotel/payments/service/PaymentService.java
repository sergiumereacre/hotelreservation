package com.hotel.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.interfaces.IPaymentCommand;
import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentEntity getPaymentByRef(String paymentRef) {
        return paymentRepository.findById(paymentRef).orElse(null);
    }

    // Process payment with card.
    public PaymentEntity processPaymentWithCard(CardEntity cardDetails) {
        IPaymentCommand cardPaymentCommand = new CardPaymentCommand(cardDetails, this);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cardPaymentCommand);
        return paymentInvoker.processPayment();
    }

    // Process payment with cash.
    public PaymentEntity processPaymentWithCash() {
        IPaymentCommand cashPaymentCommand = new CashPaymentCommand(this);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cashPaymentCommand);
        return paymentInvoker.processPayment();
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
        // Implement your card number validation logic (length, format, etc.)
        // For simplicity, let's assume the card number is valid if it's a 16-digit number.
        String cardNumberString = String.valueOf(cardNumber);
        return Pattern.matches("\\d{16}", cardNumberString);
    }

    private boolean isValidExpiryDate(String expiryDate) {
        // Implement your expiry date validation logic (format, future date, etc.)
        // For simplicity, let's assume the expiry date is valid if it's in the format MM/YY.
        return Pattern.matches("\\d{2}/\\d{2}", expiryDate);
    }

    private boolean isValidCvv(int cvv) {
        // Implement your CVV validation logic (length, format, etc.)
        // For simplicity, let's assume the CVV is valid if it's a 3-digit number.
        String cvvString = String.valueOf(cvv);
        return Pattern.matches("\\d{3}", cvvString);
    }
}
