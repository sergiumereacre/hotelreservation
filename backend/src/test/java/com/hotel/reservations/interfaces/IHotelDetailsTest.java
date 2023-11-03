package com.hotel.reservations.interfaces;

import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.repository.RoomRepository;
import com.hotel.reservations.service.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class IHotelDetailsTest {

    @Mock
    private IHotelDetails hotelDetails;
    @Mock
    private RoomRepository roomRepository;
    @InjectMocks
    private HotelService hotelService;

    private List<RoomEntity> expectedRooms;

    @BeforeEach
    void setUp() {
        RoomEntity room1 = new RoomEntity();
        room1.setRoomId(1);
        room1.setRoomNumber(101);
        room1.setCapacity(2);
        room1.setRoomType("Single");
        room1.setAvailable(true);
        room1.setPrice(100.0);

        RoomEntity room2 = new RoomEntity();
        room2.setRoomId(2);
        room2.setRoomNumber(102);
        room2.setCapacity(3);
        room2.setRoomType("Double");
        room2.setAvailable(true);
        room2.setPrice(150.0);

        // Prepare the expected list of RoomEntity objects
        expectedRooms = Arrays.asList(room1, room2);

    }



    @Test
    void getTotalAvailableCapacity_ShouldReturnCapacity() {
        // Arrange
        int expectedCapacity = 0;


        // Act
        int capacity = hotelService.getTotalAvailableCapacity();

        // Assert
        assertEquals(expectedCapacity, capacity);
    }

    @Test
    void getRooms_ShouldReturnListOfRooms() {
        // Act
        List<RoomEntity> rooms = hotelService.getRooms();

        // Assert
        assertEquals(expectedRooms, rooms);
    }

    @Test
    void getRoomById_ShouldReturnRoom() {
        // Arrange
        int roomId = 1;
        RoomEntity expectedRoom = expectedRooms.get(0); // Assuming this is the room with roomId 1
        when(roomRepository.findByRoomId(roomId)).thenReturn(Optional.of(expectedRoom));

        // Act
        RoomEntity room = hotelService.getRoomById(roomId);

        // Assert
        assertEquals(expectedRoom, room);
    }

    @Test
    void getRoomIsAvailable_ShouldReturnAvailability() {
        // Arrange
        int numGuests = 1;
        int roomId = 1;
        Date startDate = new Date();
        Date endDate = new Date();

        // Act
        boolean isAvailable = hotelService.getRoomIsAvailable(roomId, startDate, endDate);

        // Assert
        assertFalse(isAvailable);
    }


}
