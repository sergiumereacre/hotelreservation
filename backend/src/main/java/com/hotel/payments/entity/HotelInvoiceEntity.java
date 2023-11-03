package com.hotel.payments.entity;

import java.util.List;

import javax.persistence.Entity;

import com.hotel.payments.interfaces.IBilling;
import com.hotel.payments.interfaces.IChargeable;
import com.hotel.payments.interfaces.IInvoice;
import com.hotel.payments.interfaces.IInvoiceFormat;

import lombok.Data;

@Entity
@Data
public class HotelInvoiceEntity implements IInvoice {
    private List<BillEntity> bills;

    @Override
    public String printInvoice(IInvoiceFormat format) {
        return "";
    }

    @Override
    public boolean addBill(IChargeable bill) {
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
