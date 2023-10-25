package com.hotel.reservations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.reservations.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    
    @Autowired
    private HotelService service;

    @RequestMapping("/details")
    public ResponseEntity<String> getHotelDetails() {
        return ResponseEntity.ok(service.getHotelDetails());
    }
}
