package customer;

import charges.Charge;
import lombok.Getter;
import org.joda.time.LocalDate;
import rooms.Room;

import java.util.List;

public class Booking {

    @Getter private Charge charge;
    @Getter private Room room;
    @Getter private List<LocalDate> dates;

    public Booking(Charge charge, Room room, List<LocalDate> dates) {

        this.charge = charge;
        this.room = room;
        this.dates = dates;
    }
}
