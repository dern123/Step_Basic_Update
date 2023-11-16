package org.example.flight.flightDao;

import org.example.flight.model.Flight;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionFlightDaoTest {
    private static final String filePath = "flightsDataTest.json";
    private static final CollectionFlightDao collectionFlightDao = new CollectionFlightDao(filePath);

    @Test
    void loadFlightsToFile() {
        File file = new File(filePath);
        collectionFlightDao.loadFlightsToFile(filePath);
        assertTrue(file.exists());
    }

    @Test
    void saveFlights() {
        collectionFlightDao.saveFlights(filePath);
        assertFalse(collectionFlightDao.getFlightsDatabase().isEmpty());
    }

    @Test
    void getFlightById() {
        Flight flightById = collectionFlightDao.getFlightById(1);
        assertTrue(flightById.getId() >=0);
        assertTrue(flightById.getId() <=500);
        assertNotNull(flightById);
    }

    @Test
    void getFlightByUserInfo() {
        String destination = "Paris";
        String date = "11.11.2023";
        int ticketsQuantity = 2;
        List<Flight> listOfFoundFlights = collectionFlightDao.getFlightByUserInfo(destination, date, ticketsQuantity);
        assertNotNull(listOfFoundFlights);
        for (Flight flight : listOfFoundFlights) {
            assertEquals(destination, flight.getDestination());
            assertEquals(date, flight.getDate());
            assertTrue(flight.getAvailableSeatsQuantity() >= ticketsQuantity);
        }
    }

    @Test
    void getFlightsDatabase() {
        List<Flight> flightsDatabase = collectionFlightDao.getFlightsDatabase();
        assertNotNull(flightsDatabase);
        assertFalse(flightsDatabase.isEmpty());
    }
}