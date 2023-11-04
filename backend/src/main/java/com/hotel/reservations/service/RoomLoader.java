package com.hotel.reservations.service;

import com.hotel.reservations.controller.HotelController;
import com.hotel.reservations.entity.RoomEntity;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//This class is loading dummy data,will delete later
@Component
public class RoomLoader implements CommandLineRunner {

    @Autowired
    private HotelController hotelController;

    @Autowired
    private RoomFactory roomFactory;

    @Override
    public void run(String... args) throws Exception {
        initialiseRooms();
        loadRoomData(20);
    }

    private void initialiseRooms(){
        roomFactory.initialiseRooms();
    }

    private void loadRoomData(int numRooms) {
        for(int i = 0; i < numRooms; i++) {
            RoomEntity room = createRandomRoom();
            hotelController.createRoom(room);
        }
    }

    private RoomEntity createRandomRoom() {
        Random random = new Random();

        return roomFactory.createRoom(getRandomRoomType(random), getRandomNumber(random, 1, 999), getRandomDouble(random, 100, 2000));
    }

    private String getRandomRoomType(Random random) {
        String[] roomTypes = { "Single", "Double", "Twin", "Suite" };
        return roomTypes[random.nextInt(roomTypes.length)];
    }

    private int getRandomNumber(Random random, int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private double getRandomDouble(Random random, double min, double max) {
        return (random.nextDouble(max - min) + min) * 1.0;
    }
}
