package charges;

import lombok.Getter;
import rooms.Room;

public class Charge {

    private final double tax = 1.2;
    @Getter private double costBeforeTax;
    @Getter private double costAfterTax;

    public Charge(Room room, int numOfNights){

        costBeforeTax = room.getRoomCost() * numOfNights;
        costAfterTax = costBeforeTax * tax;
    }

}
