package com.hotel.reservations.service;
import com.hotel.reservations.entity.HotelEntity;
import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HotelServiceTest {

    @Mock
    private RoomRepository roomRepository;

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
        List<RoomEntity> expectedRooms = Collections.singletonList(new RoomEntity());
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
        RoomEntity expectedRoom = new RoomEntity();
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
        Date checkInDate = new Date();
        Date checkOutDate = new Date();

        // Use dates where the start date is after the end date

        // Act
        boolean isAvailable = hotelService.getRoomIsAvailable(roomId, checkInDate ,checkOutDate);

        // Assert
        assertFalse(isAvailable);
    }
}
