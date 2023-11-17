package com.hotel.payments.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.payments.entity.PaymentEntity;
import com.hotel.accounts.service.AccountService;
import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.entity.InvoiceEntity;
import com.hotel.payments.repository.PaymentCommandRepository;
import com.hotel.payments.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentCommandRepository paymentCommandRepository;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private AccountService accountService;

    public List<PaymentEntity> getAllPayments() {
        return paymentRepository.findAll();
    }

    public PaymentEntity getPaymentByRef(String paymentRef) {
        return paymentRepository.findById(paymentRef).orElse(null);
    }

    // Process payment with card.
    public void processPaymentWithCard(CardEntity cardDetails, long invoiceID) {
        InvoiceEntity invoiceEntity = invoiceService.getInvoiceById(invoiceID);
        PaymentCommand cardPaymentCommand = new CardPaymentCommand(cardDetails, invoiceEntity);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cardPaymentCommand);
        paymentInvoker.processPayment();
    }

    // Process payment with cash.
    public void processPaymentWithCash(long invoiceID) {
        InvoiceEntity invoiceEntity = invoiceService.getInvoiceById(invoiceID);
        PaymentCommand cashPaymentCommand = new CashPaymentCommand(invoiceEntity);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cashPaymentCommand);
        paymentInvoker.processPayment();
    }

    // Only callable by staff/admin
    public void validateCashPayment(long invoiceID, long callerID) {
        if(!(accountService.isStaff(callerID) || accountService.isAdmin(callerID))){
            throw new IllegalArgumentException("Only staff and admin can validate cash payments");
        }

        InvoiceEntity invoiceEntity = invoiceService.getInvoiceById(invoiceID);
        PaymentCommand cashValidateCommand = new CashValidateCommand(invoiceEntity);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cashValidateCommand);
        paymentInvoker.processPayment();
    }

    public PaymentCommand savPaymentCommand(PaymentCommand paymentCommand) {
        return paymentCommandRepository.save(paymentCommand);
    }
}
