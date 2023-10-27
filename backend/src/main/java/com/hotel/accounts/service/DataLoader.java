package com.hotel.accounts.service;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.accounts.service.GuestAccountService;
import com.hotel.accounts.service.HotelStaffAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//This class is loading dummy data,will delete later
@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private GuestAccountService guestService;

    @Autowired
    private HotelStaffAccountService staffService;

    @Override
    public void run(String... args) throws Exception {
        loadGuestData();
        loadStaffData();
    }

    private void loadGuestData() {
        GuestAccountEntity guest1 = new GuestAccountEntity();
        guest1.setName("Edison Cai");
        guest1.setEmail("edison.cai@example.com");
        guest1.setPassword("password123");
        guestService.saveGuest(guest1);

        GuestAccountEntity guest2 = new GuestAccountEntity();
        guest2.setName("Jai");
        guest2.setEmail("jai@example.com");
        guest2.setPassword("password456");
        guestService.saveGuest(guest2);

        // Add more dummy guests as needed
    }

    private void loadStaffData() {
        HotelStaffAccountEntity staff1 = new HotelStaffAccountEntity();
        staff1.setName("Jack O Brien");
        staff1.setEmail("Jack.obrien@example.com");
        staff1.setPassword("staffpassword123");
        staff1.setRole("Receptionist");
        staffService.saveStaff(staff1);

        HotelStaffAccountEntity staff2 = new HotelStaffAccountEntity();
        staff2.setName("David Walsh");
        staff2.setEmail("david.walsh@example.com");
        staff2.setPassword("staffpassword456");
        staff2.setRole("Housekeeping");
        staffService.saveStaff(staff2);
        
        HotelStaffAccountEntity staff3 = new HotelStaffAccountEntity();
        staff3.setName("Jai Jaison");
        staff3.setEmail("jai@example.com");
        staff3.setPassword("jaisocool");
        staff3.setRole("Admin");
        staffService.saveStaff(staff3);

        // Add more dummy staff as needed
    }
}