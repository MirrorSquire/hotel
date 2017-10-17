package rooms;

import customer.Booking;
import lombok.Getter;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class Room {

    @Getter protected double roomCost;
    @Getter protected boolean wiFi;
    @Getter protected List<Booking> bookings;

    public Room() {

        bookings = new ArrayList<>();
    }

    public boolean checkAvailabilityForDate(List<LocalDate> dates) {

        for(Booking booking : bookings) {

            for(LocalDate date : dates) {

                if(booking.getDates().contains(date))
                    return false;
            }
        }

        return true;
    }

    public void addToBookings(Booking booking) {

        bookings.add(booking);
    }
}
