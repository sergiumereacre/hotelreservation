package com.hotel.payments.service;

import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.interfaces.IPaymentCommand;

public class PaymentInvoker {
    // Declare the required fields.
    private IPaymentCommand paymentCommand;

    // Constructor with all the required fields.
    public void setPaymentCommand(IPaymentCommand paymentCommand) {
        this.paymentCommand = paymentCommand;
    }

    public PaymentEntity processPayment() {
        // Execute the command.
        return paymentCommand.execute();
    }
}
