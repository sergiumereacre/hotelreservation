package com.hotel.reservations.service;

import org.springframework.stereotype.Service;
import com.hotel.reservations.entity.Reservation;
import com.hotel.reservations.repository.ReservationRepository;
import java.util.Date;

@Service
public class ReservationMgt implements IReservationMgt {

        @Autowired
        private ReservationRepository repository;

        public Reservation makeReservation(int guestId, int roomId, Date startDate, Date endDate, int numGuests) {
            
        }
}
