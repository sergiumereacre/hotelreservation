package com.hotel.accounts.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.accounts.entity.AccountEntity;
import com.hotel.accounts.entity.GuestAccountEntity;
import com.hotel.accounts.entity.HotelStaffAccountEntity;
import com.hotel.accounts.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@ExtendWith(SpringExtension.class)
public class AccountControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AccountService service;

    @InjectMocks
    private AccountController controller;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllGuests_ShouldReturnGuestsList() throws Exception {
        List<GuestAccountEntity> guests = Arrays.asList(
                new GuestAccountEntity(), // Mock your GuestAccountEntity with some test data
                new GuestAccountEntity()
        );
        when(service.getAllGuests()).thenReturn(guests);

        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(guests.size())));
    }

    @Test
    public void getGuestById_ShouldReturnGuest() throws Exception {
        Long guestId = 1L;
        GuestAccountEntity guest = new GuestAccountEntity(); // Mock your GuestAccountEntity with some test data
        when(service.getGuestById(guestId)).thenReturn(guest);

        mockMvc.perform(get("/guests/{id}", guestId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    public void createGuest_ShouldCreateGuest() throws Exception {
        GuestAccountEntity guestToCreate = new GuestAccountEntity(); // Setup guest data
        GuestAccountEntity createdGuest = new GuestAccountEntity(); // This will be the mock returned object

        // Mock the creation of a new guest account to return a non-null object
        when(service.createGuestAccount()).thenReturn(new GuestAccountEntity());

        // Mock the saveGuestAccount method
        when(service.saveGuestAccount(any(GuestAccountEntity.class))).thenReturn(createdGuest);

        mockMvc.perform(post("/guests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(guestToCreate)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", not(empty())));
    }

    @Test
    public void getAllStaff_ShouldReturnStaffList() throws Exception {
        // mock data
        List<AccountEntity> staffList = new ArrayList<>();
        HotelStaffAccountEntity staff1 = new HotelStaffAccountEntity(); // Assuming AccountEntity has a default constructor and setters
        staff1.setName("John Doe");
        staff1.setEmail("john.doe@example.com");
        staffList.add(staff1);

        HotelStaffAccountEntity staff2 = new HotelStaffAccountEntity();
        staff2.setName("Jane Smith");
        staff2.setEmail("jane.smith@example.com");
        staffList.add(staff2);

        Mockito.when(service.getAllStaff()).thenReturn(staffList);

        mockMvc.perform(get("/staff")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(staffList.size())))
                .andExpect(jsonPath("$[0].name", is("John Doe")))
                .andExpect(jsonPath("$[1].name", is("Jane Smith")));
    }

    @Test
    public void getStaffById_ShouldReturnStaff() throws Exception {
        // mock data
        long staffId = 1L;
        HotelStaffAccountEntity staff = new HotelStaffAccountEntity(); // Assuming AccountEntity has a default constructor and setters
        staff.setId(staffId);
        staff.setName("John Doe");
        staff.setEmail("john.doe@example.com");

        Mockito.when(service.getStaffById(staffId)).thenReturn(staff);

        mockMvc.perform(get("/staff/{id}", staffId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(staff.getName())))
                .andExpect(jsonPath("$.email", is(staff.getEmail())));
    }
}
