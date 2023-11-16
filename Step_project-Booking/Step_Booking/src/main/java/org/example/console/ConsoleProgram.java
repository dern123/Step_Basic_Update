package org.example.console;

import org.example.flight.flightDao.CollectionFlightDao;
import org.example.flight.flightDao.FlightController;
import org.example.flight.flightDao.FlightService;
import org.example.flight.model.Flight;

import org.example.booking.controller.BookingController;
import org.example.booking.models.Booking;
import org.example.booking.models.Human;
import org.example.booking.service.BookingService;

import java.util.*;

public class ConsoleProgram {
    private final FlightController flightController;
    private final BookingController bookingController;

    public ConsoleProgram() {
        FlightService flightService = new FlightService(new CollectionFlightDao());
        BookingService bookingService = new BookingService();

        flightController = new FlightController(flightService);
        bookingController = new BookingController(bookingService);
    }

    public static void main(String[] args) {
        ConsoleProgram consoleProgram = new ConsoleProgram();
        consoleProgram.start();
    }

    public void start() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        List<Human> humans = new ArrayList<>();
        String destination;
        int idFlight = 0;

        while (isRunning) {
            try {
                System.out.println("Головне меню:");
                System.out.println("1. Онлайн-табло");
                System.out.println("2. Подивитися інформацію про рейс");
                System.out.println("3. Пошук та бронювання рейсу");
                System.out.println("4. Скасувати бронювання");
                System.out.println("5. Мої рейси");
                System.out.println("6. Вихід");
                System.out.println("Виберіть пункт меню: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> flightController.displayFlightsWithin24Hours();
                    case 2 -> {
                        System.out.println("Введіть айді рейсу: ");
                        int flightId;
                        try {
                            flightId = scanner.nextInt();
                            Flight flightById = flightController.getFlightById(flightId);
                            System.out.printf("Рейс з айді %s: %s", flightId, flightById);
                            System.out.println();
                            flightController.getFlightById(flightId);
                        } catch (InputMismatchException e) {
                            System.out.println("Помилка введення айді рейсу. Будь ласка, введіть число.");
                            scanner.next();
                        }
                    }
                    case 3 -> {
                        scanner.nextLine();
                        System.out.println("Місце призначення: ");
                        destination = scanner.nextLine();
                        scanner.nextLine();
                        System.out.println("Введіть дату (у форматі dd.MM.yyyy): ");
                        String date = scanner.nextLine();
                        System.out.println("Кількість осіб: ");
                        int passengerCount;
                        try {
                            passengerCount = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Помилка введення кількості осіб. Будь ласка, введіть число.");
                            scanner.next();
                            continue;
                        }
                        List<Human> passengers = new ArrayList<>();
                        List<Flight> foundFlights = flightController.
                                getFlightByUserInfo(destination, date, passengerCount);
                        if (foundFlights.isEmpty()) {
                            System.out.println("Рейси не знайдені.");
                        } else {
                            //рейсы с порядковым номерoм
                            for (int i = 0; i < foundFlights.size(); i++) {
                                System.out.println(i + 1 + ". " + foundFlights.get(i).toString());
                            }

                            System.out.println("Виберіть рейс за номером (або 0 для повернення до головного меню): ");
                            int selectedFlightNumber = scanner.nextInt();

                            if (selectedFlightNumber == 0) {
                                //вернутся в меню
                            } else if (selectedFlightNumber > 0 && selectedFlightNumber <= foundFlights.size()) {
                                //данные для бронирования
                                humans.clear();
                                int selectedFlightIndex = selectedFlightNumber - 1;

                                //id рейса
                                idFlight = foundFlights.get(selectedFlightIndex).getId();

                                for (int passengerIndex = 1; passengerIndex <= passengerCount; passengerIndex++) {
                                    System.out.println("Введіть ім'я пасажира " + passengerIndex + ": ");
                                    String passengerName = scanner.next();
                                    System.out.println("Введіть прізвище пасажира " + passengerIndex + ": ");
                                    String passengerLastName = scanner.next();
                                    humans.add(new Human(passengerName, passengerLastName));
                                }

                                //бронирование рейса
                                int isBookingSuccessful;
                                isBookingSuccessful = bookingController.saveBooking(humans, destination, idFlight);

                                if (isBookingSuccessful == 0) {
                                    System.out.println("Бронювання успішно завершено.");
                                    bookingController.loadDataBooking();
                                } else if (isBookingSuccessful == -1) {
                                    System.out.println("Помилка бронювання рейса.");
                                }
                            } else {
                                System.out.println("Неправильний вибір рейсу.");
                            }
                        }
                    }
                    case 4 -> {
                        System.out.println("Введіть айді бронювання: ");
                        int bookingId;
                        try {
                            bookingId = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Помилка введення айді бронювання. Будь ласка, введіть число.");
                            scanner.next();
                            continue;
                        }
                        bookingController.cancelBooking(bookingId);
                    }
                    case 5 -> {
                        System.out.println("Ім'я: ");
                        String firstName = scanner.next();
                        System.out.println("Прізвище: ");
                        String lastName = scanner.next();
                        if (firstName.isEmpty() || lastName.isEmpty()) {
                            System.out.println("Ім'я та прізвище пасажира повинні бути заповнені.");
                        } else {
                            List<Booking> allUserBookings = bookingController.getAllUserBookings(firstName, lastName);
                            System.out.println(Collections.<Booking>unmodifiableList(allUserBookings));
                        }
                    }
                    case 6 -> isRunning = false;
                    default -> System.out.println("Невірний вибір. Будь ласка, виберіть інший пункт меню.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка вводу. Введені дані не відповідають очікуваному формату.");
                scanner.next();
            }
        }
        scanner.close();
    }
}