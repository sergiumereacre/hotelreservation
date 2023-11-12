package com.hotel.application;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hotel.reservations.repository.ReservationRepository;
import com.hotel.reservations.repository.RoomRepository;
import com.hotel.reservations.service.ReservationService;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceTests {
    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private ReservationService reservationService;

    @Test
    public void testGetAllReservations() {
        
    }
}
