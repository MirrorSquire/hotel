package rooms;

import lombok.Getter;

public class Room {

    @Getter protected int roomCost;
    @Getter protected boolean wiFi;
    @Getter protected boolean available;

    public void toggleBooking(){

        available = !available;
    }
}
