package hotel;

import customer.Customer;
import customer.CustomerList;
import lombok.Getter;
import rooms.DeluxeRoom;
import rooms.PenthouseRoom;
import rooms.StandardRoom;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Hotel {

    @Getter private CustomerList customers = new CustomerList();

    @Getter private List<StandardRoom> standardRooms = Stream.generate(StandardRoom::new)
            .limit(10)
            .collect(Collectors.toList());
    @Getter private List<DeluxeRoom> deluxeRooms = Stream.generate(DeluxeRoom::new)
            .limit(5)
            .collect(Collectors.toList());
    @Getter private List<PenthouseRoom> penthouseRooms = Stream.generate(PenthouseRoom::new)
            .limit(2)
            .collect(Collectors.toList());

    public Customer login(String email){

        Customer customer = customers.findCustomer(email);

        if(customer == null) {
            System.out.println("Unable to find that customer.");
            return null;
        }
        else {
            System.out.println("Welcome back " + customer.getName() + ".");
            return customer;
        }
    }
}
