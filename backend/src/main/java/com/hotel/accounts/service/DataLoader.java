package com.hotel.accounts.service;
import com.hotel.accounts.controller.AccountController;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.reservations.controller.HotelController;
import com.hotel.reservations.entity.RoomEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//This class is loading dummy data,will delete later
@Component
public class DataLoader implements CommandLineRunner {


    @Autowired
    private AccountController accountController;

    @Autowired
    private HotelController hotelController;


    @Override
    public void run(String... args) throws Exception {
        loadGuestData();
        loadStaffData();
    }


    private void loadGuestData() {
        GuestAccountEntity guest1 = new GuestAccountEntity();
        guest1.setName("John Doe");
        guest1.setEmail("john@example.com");
        guest1.setPassword("password");
        guest1.setNumStays(1);
        accountController.createGuest(guest1);

        GuestAccountEntity guest2 = new GuestAccountEntity();
        guest2.setName("Jane Smith");
        guest2.setEmail("jane@example.com");
        guest2.setPassword("password");
        guest2.setNumStays(0);
        accountController.createGuest(guest2);

        // Add more dummy guests as needed
    }

    private void loadStaffData() {
        HotelStaffAccountEntity staff1 = new HotelStaffAccountEntity();
        staff1.setName("Alice Admin");
        staff1.setEmail("alice@example.com");
        staff1.setPassword("password");
        staff1.setRole("Staff");
        staff1.setStaff(true);
        accountController.createStaff(staff1);

        HotelStaffAccountEntity staff2 = new HotelStaffAccountEntity();
        staff2.setName("Bob Manager");
        staff2.setEmail("bob@example.com");
        staff2.setPassword("password");
        staff2.setRole("Staff");
        staff2.setStaff(true);
        accountController.createStaff(staff2);

        HotelStaffAccountEntity staff3 = new HotelStaffAccountEntity();
        staff3.setName("Jai");
        staff3.setEmail("jai@example.com");
        staff3.setPassword("jaisocool");
        staff3.setRole("Admin");
        staff3.setStaff(true);
        accountController.createStaff(staff3);

        // Add more dummy staff as needed
    }

    private void loadReservationData() {
        
        
    }


}