package bookings;

import customer.Customer;
import customer.CustomerList;
import lombok.Getter;

public class SystemManagement {

    @Getter private static boolean isLoggedIn = false;
    @Getter private static boolean exited = false;

    public static Customer login(CustomerList customers, String input){

        Customer customer = customers.findCustomer(input);

        if(customer == null) {
            System.out.println("Unable to find that customer.");
            return null;
        }
        else {
            System.out.println("Welcome back " + customer.getName() + ".");
            return customer;
        }

    }

    public static void createAccount(){

    }

    public static void logout(){

        isLoggedIn = false;
    }

    public static void exit() {

        System.exit(0);
    }
}
