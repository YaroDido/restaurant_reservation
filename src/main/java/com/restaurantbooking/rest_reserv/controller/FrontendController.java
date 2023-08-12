package com.restaurantbooking.rest_reserv.controller;

import com.restaurantbooking.rest_reserv.entity.Reservation;
import com.restaurantbooking.rest_reserv.entity.UserProfile;
import com.restaurantbooking.rest_reserv.service.ReservationService;
import com.restaurantbooking.rest_reserv.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/register-and-reserve")
public class FrontendController {
    private final UserProfileService userProfileService;
    private final ReservationService reservationService;

    @Autowired
    public FrontendController(ReservationService reservationService, UserProfileService userProfileService) {
        this.reservationService = reservationService;
        this.userProfileService = userProfileService;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model) {
        model.addAttribute("userProfile", new UserProfile());
        return "registration";
    }

    @GetMapping("/reservation")
    public String reservationForm(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservation";
    }

    @GetMapping("/successPage")
    public String successPage() {
        return "successPage";
    }

    @PostMapping("/submit-registration")
    public String submitRegistration(
            @ModelAttribute("userProfile") UserProfile userProfile,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        userProfile.setUsername(username);
        userProfile.setEmail(email);
        userProfile.setPassword(password);

        UserProfile savedUserProfile = userProfileService.createUserProfile(userProfile);
        return "redirect:/register-and-reserve/reservation";
    }

    @PostMapping("/submit-reservation")
    public String submitReservation(
            @ModelAttribute("userProfile") UserProfile userProfile,
            @RequestParam("reservationDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime reservationDateTime,
            @RequestParam("tableNumber") int tableNumber,
            @RequestParam("quantityOfCustomers") int quantityOfCustomers) {

        // Register the user profile
        UserProfile savedUserProfile = userProfileService.createUserProfile(userProfile);

        // Create a reservation
        Reservation reservation = new Reservation();
        reservation.setCustomerName(savedUserProfile.getUsername());
        reservation.setReservationDateTime(reservationDateTime);
        reservation.setTableNumber(tableNumber);
        reservation.setQuantityOfCustomers(quantityOfCustomers);
        reservationService.createReservation(reservation);

        return "redirect:/register-and-reserve/successPage";
    }

}

