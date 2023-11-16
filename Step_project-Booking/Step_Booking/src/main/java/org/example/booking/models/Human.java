package org.example.booking.models;

import java.util.Objects;

public class Human {
    private String name;
    private String surname;
    public Human(){}
    public Human(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
    @Override
    public String toString() {
        return "Passenger = {" +
                "name = '" + name + '\'' +
                ", surname = '" + surname + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(surname, human.surname);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }
}
