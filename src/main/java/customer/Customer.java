package customer;

import lombok.Getter;
import lombok.Setter;
import rooms.Room;

import java.util.List;

public class Customer {

    @Getter private String name;
    @Getter private String email;
    @Getter private List<Booking> bookings;

    @Getter @Setter private Room room;

    public Customer(String name, String email) {

        this.name = name;
        this.email = email;
    }

    public void addToBookings(Booking booking) {

        bookings.add(booking);
    }
}
