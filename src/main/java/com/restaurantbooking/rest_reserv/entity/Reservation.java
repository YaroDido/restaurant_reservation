package com.restaurantbooking.rest_reserv.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private int quantityOfCustomers;
    private int tableNumber;

    private String status;

    @Column(name = "reservation_date_time")
    private LocalDateTime reservationDateTime;

    //Constuctor
    public Reservation(Long id, String customerName, String status, int quantityOfCustomers, int tableNumber, LocalDateTime reservationDateTime) {
        this.id = id;
        this.customerName = customerName;
        this.quantityOfCustomers = quantityOfCustomers;
        this.tableNumber = tableNumber;
        this.reservationDateTime = reservationDateTime;
        this.status = status;
    }

    public Reservation() {

    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getQuantityOfCustomers() {
        return quantityOfCustomers;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }
    public String getStatus() {
        return status;
    }

//Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setQuantityOfCustomers(int partysize) {
        this.quantityOfCustomers = partysize;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
