package charges;

import rooms.Room;

public class Charge {

    private final static double tax = 1.2;

    public double getCharge(Room room) {

        return room.getRoomCost() * tax;
    }
}
