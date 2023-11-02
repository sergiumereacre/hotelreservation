package com.hotel.reservations.entity;

public class HotelEntity {
    private static HotelEntity hotelInstance;

    private String hotelDetails = "Hotel Name: Star-Crossed Hotel\n" +
            "Hotel Address: Groningen, 1234 Sesame Street, Star-Crossed Hotel\n" +
            "Hotel Phone: 123-456-7890\n" +
            "Hotel Email: starcrossedhotel@gmail.com";

    private HotelEntity() {
    }
    
    private HotelEntity(String hotelDetails) {
        this.hotelDetails = hotelDetails;
    }

    public static HotelEntity getInstance() {
        if (hotelInstance == null) {
            hotelInstance = new HotelEntity();
        }
        return hotelInstance;
    }

    public String getHotelDetails() {
        return hotelDetails;
    }
    
}