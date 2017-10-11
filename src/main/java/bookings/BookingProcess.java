package bookings;

import customer.Customer;
import customer.CustomerList;
import rooms.DeluxeRoom;
import rooms.PenthouseRoom;
import rooms.StandardRoom;

import java.util.Scanner;

public class BookingProcess {


    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEW_KEYWORD = "new";
    private static CustomerList customers = new CustomerList();

    private StandardRoom[] standardRooms = new StandardRoom[10];
    private DeluxeRoom[] deluxeRooms = new DeluxeRoom[5];
    private PenthouseRoom[] penthouseRooms = new PenthouseRoom[2];

    public static Customer login() {

        System.out.println("Welcome to hotel");
        Customer currentCustomer;
        while (true) {

            currentCustomer = null;
            System.out.println("If you are a returning customer, please enter your ID number. Otherwise, please type \"" + NEW_KEYWORD+"\" to create an account");

            String input = scanner.nextLine();
            if (!input.equals(NEW_KEYWORD)) {

                currentCustomer = customers.findCustomer(input);

                if(currentCustomer == null)
                    System.out.println("Unable to find that customer.");
                else {
                    System.out.println("Welcome back " + currentCustomer.getName() + ".");
                    return currentCustomer;
                }
            }
            else {

                System.out.println("Please enter your name");
                String name = scanner.nextLine();

                System.out.println("Please enter your email");
                String email = scanner.nextLine();

                if(customers.doesEmailAlreadyExist(email))
                    System.out.println("That email already exists");
                else {

                    currentCustomer = new Customer(name, email);
                    return currentCustomer;
                }
            }
        }
    }

    public static void bookRoom(Customer customer) {


    }
}
