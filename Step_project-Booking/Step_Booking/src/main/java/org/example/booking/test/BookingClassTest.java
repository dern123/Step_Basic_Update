package org.example.booking.test;
import org.example.booking.models.Booking;
import org.example.booking.models.Human;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BookingClassTest {
        @Test
        public void testBookingInitialization() {
            List<Human> humans = Arrays.asList(
            new Human("John", "Smith"), new Human("Jane","Smith"));
            Booking booking = new Booking(humans, 1, "Paris", 123);

            assertNotNull(booking);
            assertEquals(humans, booking.getHumans());
            assertEquals("Paris", booking.getDestination());
            assertNotNull(booking.getDateCreate());
            assertNotNull(booking.getDateUpdate());
            assertNotNull(booking.getDateCreate());
            assertEquals( "11.11.2023", booking.getDateCreate());
            assertEquals(123, booking.getIdFlight());
        }
    @Test
    public void testToString() {
        List<Human> humans = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        Booking booking = new Booking(humans, 1, "Paris", 123);

        String expectedToString = "Booking = {\n" +
                " humans count = 2\n" +
                ", humans : \n" +
                "Passenger = {name = 'John', surname = 'Smith'}\n" +
                "Passenger = {name = 'Jane', surname = 'Smith'}\n\n" +
                ", destination = Paris\n" +
                ", dateCreate = " + booking.getDateCreate() + "\n" +
                ", dateUpdate = " + booking.getDateUpdate() + "\n" +
                ", id = 1\n" +
                ", idFlight = '123'" +
                "}";
        assertEquals(expectedToString, booking.toString());
    }
    @Test
    public void testEqualsAndHashCode() {
        List<Human> humans1 = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        List<Human> humans2 = Arrays.asList(
                new Human("John", "Smith"), new Human("Jane","Smith"));
        Booking booking1 = new Booking(humans1, 1, "Paris", 123);
        Booking booking2 = new Booking(humans2, 1, "Paris", 123);

        assertTrue(booking1.equals(booking2) && booking2.equals(booking1));
        assertEquals(booking1.hashCode(), booking2.hashCode());
        }
    @Test
    public void testNotEquals() {
        List<Human> humans1 = Arrays.asList(new Human("John", "Smith"), new Human("Jane", "Smith"));
        List<Human> humans2 = Arrays.asList(new Human("John", "Smith"), new Human("Doe", "Smith"));
        Booking booking1 = new Booking(humans1, 1, "Paris", 123);
        Booking booking2 = new Booking(humans2, 1, "Paris", 123);
        assertFalse(booking1.equals(booking2) && booking2.equals(booking1));
        assertNotEquals(booking1.hashCode(), booking2.hashCode());
    }
}
