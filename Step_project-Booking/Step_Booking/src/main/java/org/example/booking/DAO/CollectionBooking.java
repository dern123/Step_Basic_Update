package org.example.booking.DAO;

import org.example.booking.jsonWorker.JsonWorker;
import org.example.booking.models.Booking;
import org.example.booking.models.Human;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CollectionBooking implements BookingDAO {
    private final String filePath = "Step_Booking/src/main/java/org/example/booking/dataBookings/data.json";
    private List<Booking> bookings;

    public CollectionBooking() {
        File file = new File(filePath);
        if (file.exists()) {
            bookings = JsonWorker.getDataFromFile(Booking.class, filePath);
        }
        if (!file.exists()){
            bookings = new ArrayList<>();

        }
    }
    public CollectionBooking(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            bookings = JsonWorker.getDataFromFile(Booking.class, filePath);
        }
        if (!file.exists()){
            bookings = new ArrayList<>();
        }
    }

    @Override
    public int saveBooking(List<Human> humans, String destination, int idFlight) {
        int id = bookings.size();
        Booking newBooking = new Booking(humans, id, destination, idFlight);
        bookings.add(newBooking);
        return id;
    }

    @Override
    public boolean cancelBooking(int bookingId) {
        if (bookingId >= 0 && bookingId < bookings.size()) {
            // Логіка для скасування бронювання
            bookings.stream().filter(booking -> booking.getId() == bookingId)
                    .findFirst()
                    .ifPresent(booking -> bookings.remove(bookingId));
            return true;
        }
        return false;
    }

    @Override
    public boolean editBooking() {
        return false;
    }

    @Override
    public List<Booking> getAllUserBookings(String name, String surname) {
        return getAllBookings().stream()
                .filter(booking -> booking.getHumans().stream()
                        .anyMatch(human ->
                                human.getName().equals(name) && human.getSurname().equals(surname)
                        ))
                .collect(Collectors.toList());
//        List<Booking> allBookings = getAllBookings();
//        List<Booking> userBookings = new ArrayList<>();
//
//        for (Booking booking : allBookings) {
//            for (Human user : booking.getHumans()){
//                // Перевірка, чи ім'я та прізвище користувача відповідають поточному бронюванню
//                if (user.getName().equals(name) && user.getSurname().equals(surname)) {
//                    userBookings.add(booking);
//                }
//            }
//        }
//        return userBookings;
    }

    public List<Booking> getAllBookings() {
        return bookings;
    }

    @Override
    public Booking findByIdBooking(int bookingId) {
        if (bookingId >= 0 && bookingId < bookings.size()) {
            return bookings.get(bookingId);
        }
        return null;
    }
}
