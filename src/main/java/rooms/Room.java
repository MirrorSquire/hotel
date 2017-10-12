package rooms;

import lombok.Getter;
import lombok.Setter;
import nights.Night;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.HashMap;

public class Room {

    @Getter protected double roomCost;
    @Getter protected boolean wiFi;
    @Getter HashMap<LocalDate, Night> nights;

    public Room() {

        //take bookings for next 5 years
        for(LocalDate date = new LocalDate(); date.isBefore(new LocalDate().plusYears(5)); date.plusDays(1)) {

            nights.put(date, new Night());
        }
    }

    public boolean checkAvailabilityForDate(LocalDate checkIn, LocalDate checkOut) {

        for(LocalDate date = checkIn; date.isBefore(checkOut); date.plusDays(1)) {

            if(nights.get(date).isBooked())
                return true;
        }

        return false;
    }

    public void bookRoomForDates(LocalDate checkIn, LocalDate checkOut) {

        for(LocalDate date = checkIn; date.isBefore(checkOut); date.plusDays(1)) {

            nights.get(date).toggleBooking();
        }
    }
}
