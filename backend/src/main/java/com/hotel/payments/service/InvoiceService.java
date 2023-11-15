package com.hotel.payments.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.service.AccountService;
import com.hotel.payments.entity.BillEntity;
import com.hotel.payments.entity.HotelInvoiceEntity;
import com.hotel.payments.entity.InvoiceEntity;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.repository.BillRepository;
import com.hotel.payments.repository.InvoiceRepository;

@Service
public class InvoiceService {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private BillRepository billRepository;

    // We must make sure that all the payments don't already belong to an invoice
    public InvoiceEntity generateInvoice(Long userId, List<String> paymentRefs) {
        List<BillEntity> billList = new ArrayList<>();
        AccountEntity guest = null;

        try {
            guest = accountService.getGuestById(userId);

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

        for (String paymentRef : paymentRefs) {
            PaymentEntity payment = paymentService.getPaymentByRef(paymentRef);

            BillEntity bill = new BillEntity(payment);
            billRepository.save(bill);

            billList.add(bill);
        }

        InvoiceEntity invoice = new HotelInvoiceEntity(billList);
        invoice.setGuest(guest);

        return saveInvoice(invoice);
    }

    public InvoiceEntity saveInvoice(InvoiceEntity invoice) {

        InvoiceEntity savedInvoice = null;

        try {
            savedInvoice = invoiceRepository.save(invoice);
        } catch (Exception e) {
            System.out.println(e);
        }

        // return invoiceRepository.save(invoice);

        return savedInvoice;
    }

}
