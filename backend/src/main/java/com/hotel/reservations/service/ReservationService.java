package com.hotel.reservations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.entity.RoomEntity;
import com.hotel.reservations.entity.RoomSettingEntity;
import com.hotel.reservations.interfaces.IReservationMgt;
import com.hotel.reservations.repository.ReservationRepository;
import com.hotel.reservations.repository.RoomSettingRepository;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService implements IReservationMgt {


    @Autowired
    private ReservationRepository repository;


    @Autowired
    private RoomSettingRepository roomSettingRepository;

    // Autowire hotelservice
    @Autowired
    private HotelService hotelService;

    public ReservationEntity saveReservation(ReservationEntity reservation) {
        return repository.save(reservation);
    }

    @Override
    public List<ReservationEntity> getAllReservations() {
        return repository.findAll();
    }

    public ReservationEntity getGuestById(String reservationRef) {
        return repository.findById(reservationRef).orElse(null);
    }

    public void deleteReservation(String reservationRef) {
        repository.deleteById(reservationRef);
    }

    @Override
    public boolean cancelReservation(String reservationRef) {
        ReservationEntity reservation = repository.findByReservationRef(reservationRef).orElse(null);

        if (reservation != null) {
            reservation.setCancelled(true);
            repository.save(reservation);
            return true;
        }

        return false;

    }

    @Override
    public ReservationEntity getReservation(String reservationRef) {
        return repository.findByReservationRef(reservationRef).orElse(null);

    }

    @Override
    public List<ReservationEntity> makeReservation(int guestId, List<Integer> roomIds, LocalDate startDate, LocalDate endDate,
            int numGuests) {

        List<ReservationEntity> reservations = new ArrayList<>();

        for (Integer i : roomIds) {
            RoomEntity room = hotelService.getRoomById(i);
            RoomSettingEntity roomSetting = new RoomSettingEntity();
            roomSettingRepository.save(roomSetting);
            // Create reservation
            ReservationEntity reservation = new ReservationEntity(room, roomSetting, guestId, numGuests,
                    startDate, endDate);

            // Save reservation
            reservations.add(reservation);
            saveReservation(reservation);
        }

        return reservations;

    }

    // @Override
    // public String showConfirmation(ReservationEntity reservation) {
    // // TODO Auto-generated method stub
    // return null;
    // }

    @Override
    public ReservationEntity updateReservation(String reservationRef, int roomId, LocalDate startDate, LocalDate endDate,
            int numGuests) {
        // TODO Auto-generated method stub
        ReservationEntity reservation = getReservation(reservationRef);
        int guestId = reservation.getGuestID();

        if (!validateReservation(guestId, roomId, startDate, endDate, numGuests)) {
            return null;
        }

        return null;
    }

    private boolean validateReservation(int guestId, int roomId, LocalDate startDate, LocalDate endDate, int numGuests) {
        return hotelService.getRoomHasCapacity(numGuests, roomId)
                && hotelService.getRoomIsAvailable(roomId, startDate, endDate);
    }

}
