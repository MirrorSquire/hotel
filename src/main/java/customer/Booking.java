package customer;

import charges.Charge;
import lombok.Getter;
import lombok.Setter;
import org.joda.time.LocalDate;
import rooms.Room;

import java.util.List;

public class Booking {

    @Getter @Setter private Charge charge;
    @Getter @Setter private Room room;
    @Getter @Setter private List<LocalDate> dates;

    public Booking(Charge charge, Room room, LocalDate checkIn, LocalDate checkOut) {

        this.charge = charge;
        this.room = room;
        for(LocalDate date = checkIn; date.isBefore(checkOut); date.plusDays(1))
            dates.add(date);
    }
}
