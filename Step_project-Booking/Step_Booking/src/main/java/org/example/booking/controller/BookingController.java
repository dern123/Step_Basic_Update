package org.example.booking.controller;

import org.example.booking.models.Booking;
import org.example.booking.models.Human;
import org.example.booking.service.BookingService;

import java.util.List;


public class BookingController {
    private BookingService bookingService;
    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }
    public List<Booking> getAllBookings() {return bookingService.getAllBookings();}
    public List<Booking> getAllUserBookings(String name, String surname) {return bookingService.getAllUserBookings(name, surname);}
    public Booking findByIdBooking(int bookingId) {return bookingService.findByIdBooking(bookingId);}
    public int saveBooking(List<Human> humans, String destination, int idFlight) { return bookingService.saveBooking( humans, destination, idFlight);}
    public void cancelBooking(int bookingId) {bookingService.cancelBooking(bookingId);}
    public void loadDataBooking() {bookingService.loadDataBooking();}
}
