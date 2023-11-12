package com.hotel.payments.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.payments.entity.BillEntity;
import com.hotel.payments.entity.HotelInvoiceEntity;
import com.hotel.payments.entity.InvoiceEntity;
import com.hotel.payments.entity.PaymentEntity;

@Service
public class InvoiceService {

    @Autowired
    private PaymentService paymentService;

    public InvoiceEntity generateInvoice(List<String> paymentRefs) {
        List<BillEntity> billList = new ArrayList<>();

        for(String paymentRef : paymentRefs) {
            PaymentEntity payment = paymentService.getPaymentByRef(paymentRef);

            BillEntity bill = new BillEntity(payment);
            billList.add(bill);
        }

        InvoiceEntity invoice = new HotelInvoiceEntity(billList);

        return invoice;
    }

}
