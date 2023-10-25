package com.hotel.reservations.service;

import org.springframework.stereotype.Service;
import com.hotel.reservations.entity.Reservation;
import com.hotel.reservations.repository.ReservationRepository;

@Service
public class ReservationMgt implements IReservationMgt {

        @Autowired
        private ReservationRepository repository;

        private BCryptPasswordEncoder passwordEncoder
}
