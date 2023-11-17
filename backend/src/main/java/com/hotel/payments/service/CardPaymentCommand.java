package com.hotel.payments.service;

import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.interfaces.IPaymentCommand;

public class CardPaymentCommand implements IPaymentCommand {
    // Declare the required fields.
    private CardEntity card;
    private PaymentService service;

    // Constructor with all the required fields.
    public CardPaymentCommand(CardEntity card, PaymentService service) {
        this.card = card;
        this.service = service;
    }

    // Execute the command.
    @Override
    public void execute() {
        // Validation logic
        service.validateCardDetails(card);
        // Process card payment.
        service.processPaymentWithCard(card);
    }
}
