package com.hotel.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.payments.entity.PaymentEntity;
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
}
