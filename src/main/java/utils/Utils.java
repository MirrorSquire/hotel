package utils;

import bookings.SystemManagement;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static LocalDate convertDate(String date) {

        DateTimeFormatter f = DateTimeFormat.forPattern("yyyy/MM/dd");

        try {
            return f.parseLocalDate(date);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public static List<LocalDate> datesToList(LocalDate checkIn, LocalDate checkOut) {

        List<LocalDate> dates = new ArrayList<>();

        for(LocalDate date = checkIn; date.isBefore(checkOut.plusDays(1)); date = date.plusDays(1)) {

            dates.add(date);
        }

        return dates;
    }
}
