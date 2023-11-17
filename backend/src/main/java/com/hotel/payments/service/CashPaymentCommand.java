package com.hotel.payments.service;

import com.hotel.payments.interfaces.IPaymentCommand;

public class CashPaymentCommand implements IPaymentCommand {
    // Declare the required fields.
    private PaymentService service;

    // Constructor with all the required fields.
    public CashPaymentCommand(PaymentService service) {
        this.service = service;
    }

    // Execute the command.
    @Override
    public void execute() {
        // Process cash payment.
        service.processPaymentWithCash();
    }
}
