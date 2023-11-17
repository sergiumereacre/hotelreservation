package com.hotel.reservations.service;

import com.hotel.accounts.service.AccountService;
import com.hotel.reservations.controller.HotelController;
import com.hotel.reservations.controller.ReservationController;
import com.hotel.reservations.entity.ReservationEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//This class is loading dummy data,will delete later
@Component
public class ReservationLoader implements CommandLineRunner {

    @Autowired
    private HotelController hotelController;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private AccountService accountService;


    @Override
    public void run(String... args) throws Exception {
        loadReservationData();
    }

    private void loadReservationData() {
        ReservationEntity reservation1 = new ReservationEntity();
        reservation1.setReservationRef("123456789");
        reservation1.setClient(accountService.getGuestById(1));
        reservation1.setStartDate("2021-04-01");
        reservation1.setEndDate("2021-04-05");
        reservation1.setNumGuests(2);
        reservation1.setCancelled(false);

        reservationService.saveReservation(reservation1);
    }
}
