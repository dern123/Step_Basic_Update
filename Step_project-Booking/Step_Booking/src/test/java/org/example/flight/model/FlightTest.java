package org.example.flight.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {
    private final Flight flight = new Flight(11, "25.11.2023", "12:45", "Paris", 34);

    @Test
    void getId() {
        assertEquals(11, flight.getId());
    }

    @Test
    void getDate() {
        assertEquals("25.11.2023", flight.getDate());
    }

    @Test
    void getTime() {
        assertEquals("12:45", flight.getTime());
    }

    @Test
    void getDestination() {
        assertEquals("Paris", flight.getDestination());
    }

    @Test
    void getAvailableSeatsQuantity() {
        assertEquals(34, flight.getAvailableSeatsQuantity());
    }

    @Test
    void testToString() {
        String flightStringExpected = "Flight{id=11, date='25.11.2023', time='12:45', destination='Paris', availableSeatsQuantity=34}";
        assertEquals(flightStringExpected, flight.toString());
    }
}