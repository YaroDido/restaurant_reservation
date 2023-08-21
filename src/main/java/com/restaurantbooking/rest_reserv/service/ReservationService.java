package com.restaurantbooking.rest_reserv.service;

import com.restaurantbooking.rest_reserv.entity.Reservation;
import com.restaurantbooking.rest_reserv.entity.UserProfile;
import com.restaurantbooking.rest_reserv.exception.TableUnavailableException;
import com.restaurantbooking.rest_reserv.exception.NotFoundException;
import com.restaurantbooking.rest_reserv.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        if (!isTableAvailableForUpdate(reservation.getTableNumber(), reservation.getReservationDateTime(), reservation.getReservationDateTime(), reservation.getCustomerName())) {
            throw new TableUnavailableException("Table is not available for the specified date and time");
        }

        reservation.setStatus("CREATED");

        return reservationRepository.save(reservation);
    }
    public Reservation updateReservation(Long reservationId, Reservation updatedReservation) {
        // Check if the reservation exists
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        if (isTableAvailableForUpdate(existingReservation.getTableNumber(),
                existingReservation.getReservationDateTime(),
                updatedReservation.getReservationDateTime(),
                existingReservation.getCustomerName())) {
            throw new TableUnavailableException("Table is not available for the updated date and time");
        }
        // Update fields
        existingReservation.setCustomerName(updatedReservation.getCustomerName());
        existingReservation.setQuantityOfCustomers(updatedReservation.getQuantityOfCustomers());
        existingReservation.setTableNumber(updatedReservation.getTableNumber());
        existingReservation.setReservationDateTime(updatedReservation.getReservationDateTime());

        existingReservation.setStatus("UPDATED");

        return reservationRepository.save(existingReservation);

    }

    private boolean isTableAvailableForUpdate
            (int tableNumber,  LocalDateTime currentReservationDateTime, LocalDateTime newReservationDateTime, String customerName) {
        // Check if the user already has a reservation on the same table and date/time
        List<Reservation> userReservations = reservationRepository.findByTableNumberAndCustomerNameAndReservationDateTime(
                tableNumber, customerName, newReservationDateTime);

        if (!userReservations.isEmpty() && !newReservationDateTime.equals(currentReservationDateTime)) {
            // User already has a reservation on this table and date/time
            return false;
        }
        List<Reservation> existingReservations = reservationRepository.findByTableNumberAndReservationDateTime(
                tableNumber, newReservationDateTime);

        if (!existingReservations.isEmpty()) {
            // Table is already reserved by another user at this date/time
            return false;
        }

        LocalDateTime maxReservationDateTime = newReservationDateTime.plusHours(3);

        return !maxReservationDateTime.isBefore(LocalDateTime.now());
    }

    public void cancelReservation(Long reservationId) {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new NotFoundException("Reservation not found"));

        // Cancel the reservation (set status or remove it)
        existingReservation.setStatus("CANCELLED");
        reservationRepository.save(existingReservation);
    }
}
