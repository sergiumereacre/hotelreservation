package com.hotel.reservations.service;

import com.hotel.reservations.entity.DoubleRoomEntity;
import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.entity.RoomSettingEntity;
import com.hotel.reservations.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository repository;

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private ReservationService reservationService;

    private ReservationEntity reservationEntity;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Use a concrete subclass of RoomEntity
        DoubleRoomEntity roomEntity = new DoubleRoomEntity();
        roomEntity.setPrice(150.0); // Set a price for the room

        RoomSettingEntity roomSettingEntity = new RoomSettingEntity();
        int guestId = 1; // Example guest ID
        Integer numberOfGuests = 2; // Example number of guests
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);


        reservationEntity = new ReservationEntity(
                roomEntity,
                roomSettingEntity,
                guestId,
                numberOfGuests,
                startDate,
                endDate
        );

        when(repository.findById(anyString())).thenReturn(java.util.Optional.of(reservationEntity));
    }

    @Test
    void saveReservation_ShouldReturnSavedReservation() {

        when(repository.save(any(ReservationEntity.class))).thenReturn(reservationEntity);

        ReservationEntity savedReservation = reservationService.saveReservation(reservationEntity);

        assertNotNull(savedReservation);
        assertEquals(reservationEntity, savedReservation);
        verify(repository).save(reservationEntity);
    }

    @Test
    void getAllReservations_ShouldReturnAllReservations() {
        final String reservationRef = "reservation123";

        ReservationEntity foundReservation = reservationService.getGuestById(reservationRef);

        assertNotNull(foundReservation);
        assertEquals(reservationEntity, foundReservation);
    }

    @Test
    void getGuestById_ShouldReturnReservation() {
        // Arrange
        String reservationRef = "ref123";
        ReservationEntity reservation = reservationEntity;
        when(repository.findById(reservationRef)).thenReturn(Optional.of(reservation));

        // Act
        ReservationEntity result = reservationService.getGuestById(reservationRef);

        // Assert
        assertEquals(reservation, result);
    }

    @Test
    void deleteReservation_ShouldInvokeDelete() {
        // Arrange
        String reservationRef = "ref123";

        // Act
        reservationService.deleteReservation(reservationRef);

        // Assert
        verify(repository).deleteById(reservationRef);
    }

    @Test
    void cancelReservation_ShouldCancelReservation() {
        // Arrange
        String reservationRef = "ref123";
        ReservationEntity reservation = reservationEntity;
        when(repository.findByReservationRef(reservationRef)).thenReturn(Optional.of(reservation));

        // Act
        boolean result = reservationService.cancelReservation(reservationRef);

        // Assert
        assertTrue(result);
        assertTrue(reservation.isCancelled());
        verify(repository).save(reservation);
    }

}
