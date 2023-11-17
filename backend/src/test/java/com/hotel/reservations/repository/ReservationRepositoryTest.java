package com.hotel.reservations.repository;

import com.hotel.reservations.entity.DoubleRoomEntity;
import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.entity.RoomSettingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@DataJpaTest
public class ReservationRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ReservationRepository reservationRepository;

    private ReservationEntity reservationEntity;

    @BeforeEach
    void setUp() {
        // Create the required entities and values to pass to the ReservationEntity constructor
        DoubleRoomEntity roomEntity = new DoubleRoomEntity();
        roomEntity.setPrice(150.0); // Set a price for the room

        // Save the RoomEntity to the database
        roomEntity = entityManager.persistAndFlush(roomEntity);
        RoomSettingEntity roomSettingEntity = new RoomSettingEntity();
        // Save the RoomSettingEntity to the database
        roomSettingEntity = entityManager.persistAndFlush(roomSettingEntity);
        int guestId = 1; // Example guest ID
        Integer numberOfGuests = 2; // Example number of guests
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(1);

        // Initialize test reservation entity with the required constructor parameters
        reservationEntity = new ReservationEntity(
                roomEntity,
                roomSettingEntity,
                guestId,
                numberOfGuests,
                startDate,
                endDate
        );

        // Persist the test reservation entity to the in-memory database
        reservationEntity = entityManager.persistAndFlush(reservationEntity);
    }
    @Test
    public void whenFindByReservationRef_thenReturnReservationEntity() {
        // When
        Optional<ReservationEntity> foundEntity = reservationRepository.findByReservationRef(reservationEntity.getReservationRef());

        // Then
        assertThat(foundEntity).isPresent();
        assertThat(foundEntity.get()).isEqualToComparingFieldByField(reservationEntity);
    }

    @Test
    public void whenFindByReservationRef_thenReturnEmpty() {
        // When
        Optional<ReservationEntity> foundEntity = reservationRepository.findByReservationRef("nonExistingRef");

        // Then
        assertThat(foundEntity).isNotPresent();
    }
}