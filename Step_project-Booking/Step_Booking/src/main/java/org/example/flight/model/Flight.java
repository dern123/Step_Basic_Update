package org.example.flight.model;

import java.util.Objects;


public class Flight {
    private int id;
    String date;
    String time;
    String departureCity;
    String destination;
    int availableSeatsQuantity;
    //    Пустий конструктор необхідний для того, щоб Jackson зміг десеріалізувати json файл, інакше виникне помилка
//    Cannot construct instance of `org.example.model.Flight`
    public Flight() {

    }

    public Flight(int id, String date, String time, String destination, int availableSeatsQuantity) {
        this.id = id;
        this.date = date;
        this.time = time;
//        Усі рейси летять з Києва, відрізнятиметься у них лише місце призначення
        this.departureCity = "Kiev";
        this.destination = destination;
        this.availableSeatsQuantity = availableSeatsQuantity;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDestination() {
        return destination;
    }

    public int getAvailableSeatsQuantity() {
        return availableSeatsQuantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Flight{");
        sb.append("id=").append(id);
        sb.append(", date='").append(date).append('\'');
        sb.append(", time='").append(time).append('\'');
        sb.append(", destination='").append(destination).append('\'');
        sb.append(", availableSeatsQuantity=").append(availableSeatsQuantity);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return id == flight.id && availableSeatsQuantity == flight.availableSeatsQuantity && Objects.equals(date, flight.date) && Objects.equals(time, flight.time) && Objects.equals(destination, flight.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, destination, availableSeatsQuantity);
    }
}
