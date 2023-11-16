package org.example.booking.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.booking.DAO.CollectionBooking;
import org.example.booking.controller.BookingController;
import org.example.booking.models.Booking;
import org.example.booking.models.Human;
import org.example.booking.service.BookingService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BookingControllerTest {
    private String filePath = "DataTest.json";
    CollectionBooking collectionBooking = new CollectionBooking(filePath);
    BookingService bookingService = new BookingService(collectionBooking);
    BookingController bookingController = new BookingController(bookingService);
    @Test
    public void getAllBookings() {
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        List<Human> humans2 = Arrays.asList(
                new Human("John", "Sm"), new Human("Jane","Sm"));
        bookingController.saveBooking(humans,"Lviv",1);
        bookingController.saveBooking(humans2,"Riga",2);
        List<Booking> expectedBookings = Arrays.asList(
                new Booking(humans, 0,"Lviv", 1),
                new Booking(humans2, 1,"Riga", 2)
        );
        List<Booking> expectedBookings2 = new ArrayList<>();
        expectedBookings2.add(new Booking(humans, 0,"Lviv", 1));
        expectedBookings2.add(new Booking(humans2, 1,"Riga", 2));

        List<Booking> savedBookings = bookingController.getAllBookings();
        assertNotNull(bookingController.getAllBookings());
        assertEquals(expectedBookings, savedBookings);
        assertEquals(expectedBookings2, savedBookings);
    }
    @Test
    public void getAllUserBookings() {
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        List<Human> humans2 = Arrays.asList(
                new Human("John", "Sm"), new Human("Jane","Sm"));
        bookingController.saveBooking(humans,"Lviv",1);
        bookingController.saveBooking(humans,"Lviv",1);
        bookingController.saveBooking(humans2,"Riga",3);
        String s = "Booking = {\n" +
                " humans count = 2\n" +
                ", humans : \n" +
                "Passenger = {name = 'John', surname = 'Sm'}\n" +
                "Passenger = {name = 'Jane', surname = 'Sm'}\n\n" +
                ", destination = Riga\n" +
                ", dateCreate = " + "11.11.2023" + "\n" +
                ", dateUpdate = " + "11.11.2023" + "\n" +
                ", id = 2\n" +
                ", idFlight = '3'" +
                "}";
        List<Booking> savedBookings = bookingController.getAllUserBookings("John", "Sm");
        assertNotNull(bookingController.getAllUserBookings("John","Sm"));
        for (Booking booking: savedBookings ) {
            assertEquals(humans2, booking.getHumans());
            assertEquals(s, booking.toString());
        }
    }
    @Test
    public void findByIdBooking() {
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        bookingController.saveBooking(humans,"Lviv",1);
        Booking savedBooking = bookingController.findByIdBooking(0);
        assertNotNull(bookingController.findByIdBooking(0));
        assertEquals(humans, savedBooking.getHumans());
        assertEquals("Lviv", savedBooking.getDestination());
        assertEquals( "11.11.2023", savedBooking.getDateCreate());
        assertEquals("11.11.2023", savedBooking.getDateUpdate());
        assertEquals(0, savedBooking.getId());
        assertEquals(1, savedBooking.getIdFlight());
    }
    @Test
    public void saveBooking() {
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        bookingController.saveBooking(humans,"Lviv",1);
        assertNotNull(bookingController.getAllBookings());
        assertEquals(1, bookingController.getAllBookings().size());
        Booking savedBooking = bookingController.getAllBookings().get(0);
        assertEquals(humans, savedBooking.getHumans());
        assertEquals("Lviv", savedBooking.getDestination());
        assertEquals( "11.11.2023", savedBooking.getDateCreate());
        assertEquals("11.11.2023", savedBooking.getDateUpdate());
        assertEquals(0, savedBooking.getId());
        assertEquals(1, savedBooking.getIdFlight());
    }
    @Test
    public void cancelBooking() {
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        List<Human> humans2 = Arrays.asList(
                new Human("John", "Sm"), new Human("Jane","Sm"));
        bookingController.saveBooking(humans,"Lviv",1);
        bookingController.saveBooking(humans2,"Riga",2);
        String s = "Booking = {\n" +
                " humans count = 2\n" +
                ", humans : \n" +
                "Passenger = {name = 'John', surname = 'Sm'}\n" +
                "Passenger = {name = 'Jane', surname = 'Sm'}\n\n" +
                ", destination = Riga\n" +
                ", dateCreate = " + "11.11.2023" + "\n" +
                ", dateUpdate = " + "11.11.2023" + "\n" +
                ", id = 1\n" +
                ", idFlight = '2'" +
                "}";
        bookingController.cancelBooking(0);
        for (Booking booking: bookingController.getAllBookings() ) {
            assertEquals(humans2, booking.getHumans());
            assertEquals(s, booking.toString());
        }
    }
    @Test
    public void loadDataBookingTest() {
        createTestFile();
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));



        File file = new File(filePath);
        CollectionBooking collectionBookingTest = new CollectionBooking(filePath);
        BookingService bookingService1 = new BookingService(collectionBookingTest);
        BookingController bookingController1 = new BookingController(bookingService1);
        bookingController1.saveBooking(humans,"Lviv",1);
        bookingController1.loadDataBooking();
        assertNotNull(bookingController1.getAllBookings());
        assertEquals(2, bookingController1.getAllBookings().size());
    }
    private void createTestFile() {
        try {
            List<Human> humans = Arrays.asList(
                    new Human("Alan", "Shoot"), new Human("Jane","Shoot"));
            // Create a list of Booking objects for testing
            List<Booking> testBookings = Arrays.asList( new Booking(humans,1,"Lviv",1) );
            // Use Jackson ObjectMapper to write JSON data to the file
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File(filePath), testBookings);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your application's needs
        }
    }
}
