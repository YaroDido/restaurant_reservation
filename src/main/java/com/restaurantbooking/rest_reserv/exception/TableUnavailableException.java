package com.restaurantbooking.rest_reserv.exception;

public class TableUnavailableException extends RuntimeException{
    public TableUnavailableException(String message){
        super(message);
    }
}
