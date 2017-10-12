package rooms;

import lombok.Getter;
import lombok.Setter;
import nights.Night;

import java.util.List;

public class Room {

    @Getter protected int roomCost;
    @Getter protected boolean wiFi;
    @Getter protected boolean available;
    @Getter @Setter List<Night> nightsBooked;

    public void toggleBooking(){

        available = !available;
    }
}
