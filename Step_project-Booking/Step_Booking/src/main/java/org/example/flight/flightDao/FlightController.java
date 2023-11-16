package org.example.flight.flightDao;


import org.example.flight.model.Flight;

import java.util.List;


public class FlightController {
    private FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public void displayFlightsWithin24Hours() {
        flightService.displayFlightsWithin24Hours();
    }

    public void loadFlightsToFile() {
        flightService.loadFlightsToFile();
    }

    public void saveFlights() {
        flightService.saveFlights();
    }

    public Flight getFlightById(int id) {
        return flightService.getFlightById(id);
    }

    public List<Flight> getFlightByUserInfo(String destination, String date, int ticketsQuantity) {
        return flightService.getFlightByUserInfo(destination, date, ticketsQuantity);
    }

    public List<Flight> getFlightsDatabase() {
        return flightService.getFlightsDatabase();
    }
}
