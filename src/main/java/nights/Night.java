package nights;

import lombok.Getter;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Night {

    @Getter boolean booked;

    public Night() {

        booked = false;
    }

    public void toggleBooking() {

        booked = !booked;
    }
}
