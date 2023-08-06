package com.restaurantbooking.rest_reserv.repository;

import com.restaurantbooking.rest_reserv.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByTableNumberAndCustomerNameAndReservationDateTime
            (int tableNumber, String customerName, LocalDateTime reservationDateTime);

    List<Reservation> findByTableNumberAndReservationDateTime
            (int tableNumber, LocalDateTime reservationDateTime);
}
