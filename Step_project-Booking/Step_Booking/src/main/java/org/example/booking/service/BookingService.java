package org.example.booking.service;

import org.example.booking.DAO.BookingDAO;
import org.example.booking.DAO.CollectionBooking;
import org.example.booking.jsonWorker.JsonWorker;
import org.example.booking.models.Booking;
import org.example.booking.models.Human;

import java.io.File;
import java.util.List;

public class BookingService {
    private final String filePath = "Step_project-Booking/Step_Booking/src/main/java/org/example/booking/dataBookings/data.json";
    private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new CollectionBooking();
    }
    public BookingService(CollectionBooking collectionBooking) {this.bookingDAO = collectionBooking;}

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public List<Booking> getAllUserBookings(String name, String surname) {
        if (name.isEmpty() || surname.isEmpty()) {
            throw new IllegalArgumentException("Exception name or surname");
        } else {
            return bookingDAO.getAllUserBookings(name, surname);
        }
    }

    public Booking findByIdBooking(int bookingId) {
        try {
            return bookingDAO.findByIdBooking(bookingId);
        } catch (Exception err) {
            throw new IllegalArgumentException("Exception id Booking");
        }
    }

    public int saveBooking(List<Human> humans, String destination, int idFlight) {
        if (humans.size() > 0 && idFlight >= 0) {
            return bookingDAO.saveBooking(humans, destination, idFlight);
        }
        return -1; // Повернення значення за умови, що умова не виконана
    }

    public void cancelBooking(int bookingId) {
        bookingDAO.cancelBooking(bookingId);
    }

    public void loadDataBooking() {
        File file = new File(filePath);
        if (file.exists()) {
            List<Booking> bookings = bookingDAO.getAllBookings();
            JsonWorker.loadDataToFile(bookings, filePath);
        }
    }
}
