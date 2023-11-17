package com.hotel.payments.service;

import com.hotel.payments.interfaces.IPaymentCommand;
import com.hotel.payments.entity.PaymentEntity;

public class CashPaymentCommand implements IPaymentCommand {
    // Declare the required fields.
    private PaymentService service;

    // Constructor with all the required fields.
    public CashPaymentCommand(PaymentService service) {
        this.service = service;
    }

    // Execute the command.
    @Override
    public PaymentEntity execute() {
        // Process cash payment.
        return service.processPaymentWithCash();
    }
}
