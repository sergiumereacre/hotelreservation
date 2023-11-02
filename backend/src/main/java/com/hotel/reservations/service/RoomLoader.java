package com.hotel.reservations.service;

import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.reservations.controller.HotelController;
import com.hotel.reservations.entity.RoomEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

    //This class is loading dummy data,will delete later
    @Component
    public class RoomLoader implements CommandLineRunner {


        @Autowired
        private HotelController hotelController;


        @Override
        public void run(String... args) throws Exception {
            loadRoomData();
        }


        private void loadReservationData() {


        }

        private void loadRoomData() {
            RoomEntity room1 = new RoomEntity();
            room1.setRoomType("Single");
            room1.setAvailable(true);
            room1.setRoomNumber(123);
            room1.setCapacity(1);
            room1.setPrice(100.00);
            hotelController.createRoom(room1);

            // // Create more dummy rooms as needed
            // RoomEntity room2 = new RoomEntity(1, 1234, 1, "Single", true, 100.00);
            // hotelController.createRoom(room2);
        }
    }

