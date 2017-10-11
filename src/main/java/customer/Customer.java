package customer;

import lombok.Getter;
import lombok.Setter;
import rooms.Room;

public class Customer {

    @Getter private String name;
    @Getter private String email;
    @Getter @Setter private Room room;

    public Customer(String name, String email) {

        this.name = name;
        this.email = email;
    }
}
