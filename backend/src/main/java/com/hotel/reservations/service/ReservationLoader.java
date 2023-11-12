package com.hotel.reservations.service;

import com.hotel.reservations.controller.HotelController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//This class is loading dummy data,will delete later
@Component
public class ReservationLoader implements CommandLineRunner {

    @Autowired
    private HotelController hotelController;


    @Override
    public void run(String... args) throws Exception {
    }

    private void loadReservationData() {

    }
}
