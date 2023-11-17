package com.hotel.reservations.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.hotel.payments.entity.PaymentEntity;
import com.hotel.reservations.entity.ReservationEntity;
import com.hotel.reservations.service.ReservationService;
import com.hotel.reservations.service.EngageReservationService;
import com.hotel.reservations.service.PreferenceService;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EngageReservationService engageReservationService;

    @Autowired
    private PreferenceService preferenceService;

    @GetMapping("/reservation/{reservationRef}")
    public ResponseEntity<ReservationEntity> getReservationByRef(@PathVariable String reservationRef) {
        return ResponseEntity.ok(reservationService.getReservation(reservationRef));
    }

    @GetMapping("{userId}/all")
    public ResponseEntity<List<ReservationEntity>> getAllReservations(@PathVariable long userId) {
        return ResponseEntity.ok(reservationService.getAllReservations(userId));
    }

    @PutMapping("/reservation/{reservationRef}/preferences")
    public void updatePreferences(String reservationRef, String theme, double temperature,
            int lighting) {
        preferenceService.setPreference(reservationRef, theme, temperature, lighting);
    }

    @PutMapping("/reservation/{reservationRef}/check-in")
    public ResponseEntity<Boolean> checkIn(@PathVariable String reservationRef) {
        boolean ok = engageReservationService.doCheckIn(reservationRef);
        return ResponseEntity.ok(ok);
    }

    @PutMapping("/reservation/{reservationRef}/check-out")
    public ResponseEntity<Boolean> checkOut(@PathVariable String reservationRef) {
        boolean ok = engageReservationService.doCheckOut(reservationRef);
        return ResponseEntity.ok(ok);
    }

    @PutMapping("/reservation/{reservationRef}/cancel")
    public ResponseEntity<Boolean> cancelReservation(@PathVariable String reservationRef) {
        boolean ok = reservationService.cancelReservation(reservationRef);
        return ResponseEntity.ok(ok);
    }

    // Request to update reservation
    @PutMapping("/reservation/{reservationRef}")
    public ResponseEntity<ReservationEntity> updateReservation(@PathVariable String reservationRef, int roomId,
            LocalDate startDate, LocalDate endDate, int numGuests) {
        ReservationEntity reservation = reservationService.updateReservation(reservationRef, roomId, startDate, endDate,
                numGuests);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("/make-reservations")
    public ResponseEntity<?> makeReservation(@RequestBody JsonNode payload) {

        // System.out.println(payload.toString());

        int guestId = payload.get("guestId").asInt();

        JsonNode roomListJson = payload.get("roomIds");

        List<Integer> roomIdList = new ArrayList<>();
        if (roomListJson.isArray()) {
            for (JsonNode roomIdJson : roomListJson) {
                roomIdList.add(roomIdJson.asInt());
            }
        }

        LocalDate startDate = LocalDate.parse(payload.get("startDate").asText());
        LocalDate endDate = LocalDate.parse(payload.get("endDate").asText());
        int numGuests = payload.get("numGuests").asInt();

        List<ReservationEntity> reservation = null;

        // try {
           
        // } catch (Exception e) {
        //     System.out.println(e);
        // }

         reservation = reservationService.makeReservation(guestId, roomIdList, startDate, endDate,
                    numGuests);

        return ResponseEntity.ok(reservation);
    }
}