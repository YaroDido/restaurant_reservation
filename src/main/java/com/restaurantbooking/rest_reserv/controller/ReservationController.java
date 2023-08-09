package com.restaurantbooking.rest_reserv.controller;

import com.restaurantbooking.rest_reserv.entity.Reservation;
import com.restaurantbooking.rest_reserv.entity.UserProfile;
import com.restaurantbooking.rest_reserv.service.ReservationService;
import com.restaurantbooking.rest_reserv.service.UserProfileService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {
    public final ReservationService reservationService;
    public final UserProfileService userProfileService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserProfileService userProfileService) {
        this.reservationService = reservationService;
        this.userProfileService = userProfileService;
    }
    @PostMapping("/")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
//        UserProfile userProfile = userProfileService.getUserProfileByUsername(reservation.getCustomerName());
//
//        if(userProfile == null){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        reservation.setCustomerName(userProfile.getUsername());

        Reservation createdReservation = reservationService.createReservation(reservation);
        return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);

    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationId,
                                                         @RequestBody Reservation updatedReservation) {
        Reservation updated = reservationService.updateReservation(reservationId, updatedReservation);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{reservationId}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable Long reservationId) {
        reservationService.cancelReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
