package rooms;

import customer.Booking;
import lombok.Getter;
import lombok.Setter;
import nights.Night;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Room {

    @Getter protected double roomCost;
    @Getter protected boolean wiFi;
    @Getter protected List<Booking> bookings;
    //@Getter HashMap<LocalDate, Night> nights;

    public Room() {

        bookings = Collections.emptyList();
    }

//    public boolean checkAvailabilityForDate(LocalDate checkIn, LocalDate checkOut) {
//
//        for(LocalDate date = checkIn; date.isBefore(checkOut); date.plusDays(1)) {
//
//            if(nights.get(date).isBooked())
//                return true;
//        }
//
//        return false;
//    }

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
