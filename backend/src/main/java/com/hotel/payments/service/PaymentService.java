package com.hotel.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IPayment;
import com.hotel.payments.repository.PaymentRepository;

@Service
public class PaymentService implements IPayment {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public boolean processPayment(int invoiceId, String paymentType) {
        return false;
    }

    @Override
    public IInvoice generateIInvoice(List<IBilling> billings) {
        return null;
    }

    public List<IInvoice> getInvoiceHistory(int userId) {
        return null;
    }

    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }
}
