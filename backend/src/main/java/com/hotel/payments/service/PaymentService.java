package com.hotel.payments.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IPayment;

@Service
public class PaymentService implements IPayment {
    @Override
    public boolean processPayment(IInvoice invoice, String paymentType) {
        return false;
    }

    @Override
    public IInvoice generateIInvoice(List<IBilling> billings) {
        return null;
    }
}
