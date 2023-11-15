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
import com.hotel.payments.interfaces.IPayment;
import com.hotel.payments.repository.BillRepository;
import com.hotel.payments.repository.InvoiceRepository;

@Service
public class InvoiceService implements IPayment {

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

            if(payment == null){
                continue;
            }

            BillEntity bill = new BillEntity(payment);
            billRepository.save(bill);

            billList.add(bill);
        }

        if(billList.size() == 0){
            return null;
        }

        InvoiceEntity invoice = new HotelInvoiceEntity(guest, billList);
        // invoice.setGuest(guest);

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

    public List<InvoiceEntity> getInvoicesByIsPaid(Long userId, boolean isPaid) {
        GuestAccountEntity guest = accountService.getGuestById(userId);

        List<InvoiceEntity> invoices = null;

        // return invoiceRepository.findAllByGuestAndIsPaid(guest, isPaid);

        try{
            invoices = invoiceRepository.findAllByGuestAndIsPaid(guest, isPaid);
        } catch (Exception e) {
            System.out.println(e);
        }

        return invoices;
    }

    public InvoiceEntity getInvoiceById(Long invoiceId) {
        return invoiceRepository.findById(invoiceId).orElse(null);
    }

    @Override
    public boolean processPayment(Long invoiceId, String paymentType) {

        InvoiceEntity invoice = null;

        try {
            invoice = invoiceRepository.findById(invoiceId).orElse(null);
        } catch (Exception e) {
            System.out.println(e);
        }

        if (invoice == null) {
            return false;
        }

        invoice.setPaid(true);
        invoice.setPaymentType(paymentType);

        saveInvoice(invoice);
        
        return true;
    }
}
