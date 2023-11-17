package com.hotel.reservations.service;

import com.hotel.reservations.entity.DoubleRoomEntity;
import com.hotel.reservations.entity.HotelEntity;
import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.repository.ReservationRepository;
import com.hotel.reservations.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class HotelServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private HotelService hotelService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getHotelDetails_ReturnsDetails() {
        // Arrange
        String expectedDetails = "Hotel XYZ Details";
        try (MockedStatic<HotelEntity> mockedStatic = Mockito.mockStatic(HotelEntity.class)) {
            mockedStatic.when(HotelEntity::getInstance).thenReturn(mock(HotelEntity.class));
            when(HotelEntity.getInstance().getHotelDetails()).thenReturn(expectedDetails);

            // Act
            String details = hotelService.getHotelDetails();

            // Assert
            assertEquals(expectedDetails, details);
        }
    }

    @Test
    public void getRooms_ReturnsListOfRooms() {
        // Arrange
        List<RoomEntity> expectedRooms = Collections.singletonList(new DoubleRoomEntity());
        when(roomRepository.findAll()).thenReturn(expectedRooms);

        // Act
        List<RoomEntity> rooms = hotelService.getRooms();

        // Assert
        assertEquals(expectedRooms, rooms);
    }

    @Test
    public void getRoomById_ReturnsRoom() {
        // Arrange
        int roomId = 1;
        RoomEntity expectedRoom = new DoubleRoomEntity();
        when(roomRepository.findByRoomId(roomId)).thenReturn(Optional.of(expectedRoom));

        // Act
        RoomEntity room = hotelService.getRoomById(roomId);

        // Assert
        assertEquals(expectedRoom, room);
    }

    @Test
    public void getRoomIsAvailable_ReturnsFalseWhenStartDateAfterEndDate() {
        // Arrange
        int roomId = 1;
        LocalDate checkInDate = LocalDate.now().plusDays(1);
        LocalDate checkOutDate = LocalDate.now();

        // Act
        boolean isAvailable = hotelService.getRoomIsAvailable(roomId, checkInDate, checkOutDate);

        // Assert
        assertFalse(isAvailable); // Expecting false because the check-in date is after the check-out date
    }

}
