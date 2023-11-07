package com.hotel.payments.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IInvoiceFormat;
import com.hotel.reservations.entity.ReservationEntity;

import lombok.Data;

@Entity
@Data
public class HotelInvoiceEntity implements IInvoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int invoiceID;

    private int guestID;

    @OneToMany
    private List<BillEntity> bills;

    @Override
    public String printInvoice(IInvoiceFormat format) {
        return "";
    }

    @Override
    public boolean addBill(PaymentEntity bill) {
        this.bills.add(new BillEntity(bill, false));

        return true;
    }

    @Override
    public void removeBill(IBilling bill) {
        this.bills.remove(bill);
    }

    @Override
    public void setRefund(IBilling bill, double refundAmount) {
        for (BillEntity billEntity : this.bills) {
            if (billEntity.getPayment().equals(bill)) {
                billEntity.setRefundableAmount(refundAmount);
                billEntity.setRefundable(true);
            }
        }
    }

    @Override
    public double getTotal() {
        double total = 0;

        for (BillEntity billEntity : this.bills) {
            total += billEntity.calculateBill();
        }

        return total;
    }
}
