package org.example.booking.models;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Booking {
    private List<Human> humans;
    private String destination;
    private String dateCreate;
    private String dateUpdate;
    private int id;
    private int idFlight;
    public Booking(){}
    public Booking(List<Human> humans, int id, String destination, int idFlight ) {
        this.humans = humans;
        this.destination = destination;
        long today = LocalDate.now().toEpochDay() * 86_400_000;
        this.dateCreate = new SimpleDateFormat("dd.MM.yyyy").format(new Date(today));
        this.dateUpdate = new SimpleDateFormat("dd.MM.yyyy").format(new Date(today));
        this.id = id;
        this.idFlight = idFlight;
    }
    public List<Human> getHumans() {return humans;}
    public void setHumans(List<Human> humans) {this.humans = humans;}
    public String getDateCreate() {return dateCreate;}
    public String getDateUpdate() {return dateUpdate;}
    public void setDateUpdate(String dateUpdate) {this.dateUpdate = dateUpdate;}
    public int getId() {return id;}
    public String getDestination() {return destination;}
    public void setDestination(String destination) {this.destination = destination;}
    public  int getIdFlight(){ return idFlight; }
    @Override
    public String toString() {
        StringBuilder humansList = new StringBuilder();
        for (Human human: humans) {
            humansList.append(human.toString()).append("\n");
        }
        final StringBuilder sb = new StringBuilder()
                .append("Booking = {").append('\n')
                .append(" humans count = ").append(humans.size()).append('\n')
                .append(", humans : ").append('\n')
                .append(humansList).append('\n')
                .append(", destination = ").append(destination).append('\n')
                .append(", dateCreate = ").append(dateCreate).append('\n')
                .append(", dateUpdate = ").append(dateUpdate).append('\n')
                .append(", id = ").append(id).append('\n')
                .append(", idFlight = '").append(idFlight).append('\'')
                .append('}');
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id == booking.id && Objects.equals(humans, booking.humans) && Objects.equals(dateCreate, booking.dateCreate) && Objects.equals(dateUpdate, booking.dateUpdate) && Objects.equals(destination, booking.destination);
    }
    @Override
    public int hashCode() {
        return Objects.hash(humans, dateCreate, dateUpdate, id, destination);
    }
}
