package com.hotel.payments.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.service.AccountService;
import com.hotel.payments.entity.BillEntity;
import com.hotel.payments.entity.CardEntity;
import com.hotel.payments.entity.HotelInvoiceEntity;
import com.hotel.payments.entity.InvoiceEntity;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.payments.interfaces.IPayment;
import com.hotel.payments.repository.BillRepository;
import com.hotel.payments.repository.CardRepository;
import com.hotel.payments.repository.InvoiceRepository;
import com.hotel.payments.repository.PaymentCommandRepository;

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

    @Autowired
    private InvoiceFormatFactory invoiceFormatFactory;

    @Autowired
    private PaymentCommandRepository paymentCommandRepository;

    @Autowired
    private CardRepository cardRepository;

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

            if (payment == null) {
                continue;
            }

            BillEntity bill = new BillEntity(payment);
            billRepository.save(bill);

            billList.add(bill);
        }

        if (billList.size() == 0) {
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

        try {
            invoices = invoiceRepository.findAllByGuestAndIsPaid(guest, isPaid);
        } catch (Exception e) {
            System.out.println(e);
        }

        return invoices;
    }

    public InvoiceEntity getInvoiceById(long invoiceId) {
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

    public String getFormattedInvoice(Long invoiceId, String format) {
        InvoiceFormat invoiceFormat = invoiceFormatFactory.createFormat(format);

        InvoiceEntity invoice = getInvoiceById(invoiceId);

        if (invoice == null) {
            return "";
        }

        invoiceFormat.setInvoice(invoice);
        return invoiceFormat.format();
    }

    // Process payment with cash.
    public void processPaymentWithCash(long invoiceID) {
        InvoiceEntity invoiceEntity = getInvoiceById(invoiceID);
        PaymentCommand cashPaymentCommand = new CashPaymentCommand(invoiceEntity);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cashPaymentCommand);

        try {
            paymentInvoker.processPayment();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        saveInvoice(invoiceEntity);
        savePaymentCommand(cashPaymentCommand);
    }

    // Only callable by staff/admin
    public void validateCashPayment(long invoiceID, long callerID) {
        if (!(accountService.isStaff(callerID) || accountService.isAdmin(callerID))) {
            throw new IllegalArgumentException("Only staff and admin can validate cash payments");
        }

        InvoiceEntity invoiceEntity = getInvoiceById(invoiceID);
        PaymentCommand cashValidateCommand = new CashValidateCommand(invoiceEntity);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cashValidateCommand);

        try {
            paymentInvoker.processPayment();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        saveInvoice(invoiceEntity);
        savePaymentCommand(cashValidateCommand);
    }

    // Process payment with card.
    public void processPaymentWithCard(CardEntity cardDetails, long invoiceID) {
        InvoiceEntity invoiceEntity = getInvoiceById(invoiceID);
        PaymentCommand cardPaymentCommand = new CardPaymentCommand(cardDetails, invoiceEntity);
        PaymentInvoker paymentInvoker = new PaymentInvoker();
        paymentInvoker.setPaymentCommand(cardPaymentCommand);

        try {
            paymentInvoker.processPayment();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        saveCardEntity(cardDetails);
        saveInvoice(invoiceEntity);
        savePaymentCommand(cardPaymentCommand);
    }

    public PaymentCommand savePaymentCommand(PaymentCommand paymentCommand) {
        return paymentCommandRepository.save(paymentCommand);
    }

    public CardEntity saveCardEntity(CardEntity cardEntity) {
        return cardRepository.save(cardEntity);
    }

}
