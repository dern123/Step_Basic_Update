package org.example.flight.flightDao;


import org.example.flight.model.Flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


public class FlightService {
    private CollectionFlightDao collectionFlightDao;

    public FlightService(CollectionFlightDao collectionFlightDao) {
        this.collectionFlightDao = collectionFlightDao;
    }

    public void displayFlightsWithin24Hours() {
        System.out.println("\n----- Fligths within 24 hours -----\n");
        getFlightsWithin24Hours().forEach(System.out::println);
    }

    public List<Flight> getFlightsWithin24Hours() {
        List<Flight> flightsDatabase = collectionFlightDao.getFlightsDatabase();
        List<Flight> flightsWithin24Hours = new ArrayList<>();

//        Отримуємо сьогоднішню дату та час
        LocalDateTime dateNow = LocalDateTime.now();

//        Отримуємо дату та час через добу
        LocalDateTime dateAfter24Hours = dateNow.plusHours(24);

//        Проходимося циклом по базі даних рейсів та перевіряємо, щоб дата рейсу
//        була в межах 24 годин
        flightsDatabase.forEach(flight -> {
            LocalDateTime dateOfFlight = null;
            try {
                dateOfFlight = getDateOfFlight(flight);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (dateOfFlight.isAfter(dateNow) && dateOfFlight.isBefore(dateAfter24Hours)) {
                flightsWithin24Hours.add(flight);
            }
        });

//        Повертаємо заповнений список flightsWithin24Hours
        return flightsWithin24Hours;
    }

    public LocalDateTime getDateOfFlight(Flight flight) throws ParseException {
//        Робимо стрінгу з даних рейсу, форматом dd.MM.yyyy HH:mm, наприклад 07.11.2023 13:51
        String flightDate = flight.getDate();
        String flightTime = flight.getTime();
        String dateAndTime = flightDate + " " + flightTime;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
//        Перетворюємо стрінгу в формат Date, а далі в формат toLocalDateTime
        return simpleDateFormat.parse(dateAndTime)
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public void loadFlightsToFile() {
        collectionFlightDao.loadFlightsToFile();
    }

    public void saveFlights() {
        collectionFlightDao.saveFlights();
    }

    public Flight getFlightById(int id) {
        return collectionFlightDao.getFlightById(id);
    }

    public List<Flight> getFlightByUserInfo(String destination, String date, int ticketsQuantity) {
        return collectionFlightDao.getFlightByUserInfo(destination, date, ticketsQuantity);
    }

    public List<Flight> getFlightsDatabase() {
        return collectionFlightDao.getFlightsDatabase();
    }
}
