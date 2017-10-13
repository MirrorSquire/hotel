package bookings;

import charges.Charge;
import customer.Booking;
import customer.Customer;
import customer.CustomerList;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import rooms.DeluxeRoom;
import rooms.PenthouseRoom;
import rooms.Room;
import rooms.StandardRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class BookingProcess {


    private final Scanner scanner = new Scanner(System.in);
    private final String NEW_KEYWORD = "new";

    private CustomerList customers;

    private List<StandardRoom> standardRooms;
    private List<DeluxeRoom> deluxeRooms;
    private List<PenthouseRoom> penthouseRooms;

    private final String roomSelectionRegex = "[SDP]";

    public BookingProcess(CustomerList customers, List<StandardRoom> standardRooms, List<DeluxeRoom> deluxeRooms, List<PenthouseRoom> penthouseRooms) {

        this.customers = customers;
        this.standardRooms = standardRooms;
        this.deluxeRooms = deluxeRooms;
        this.penthouseRooms = penthouseRooms;
    }

    public void start() {

        Customer customer = login();
        findAvailableRoom(customer);
    }

    public Customer login() {

        System.out.println("Welcome to hotel");
        Customer currentCustomer;
        while (true) {

            System.out.println("If you are a returning customer, please enter your ID number. Otherwise, please type \"" + NEW_KEYWORD+"\" to create an account. Type E to exit");

            String input = scanner.nextLine();

            checkForExit(input);

            if (!input.equals(NEW_KEYWORD)) {

                currentCustomer = SystemManagement.login(customers, input);

                if(currentCustomer != null)
                    return currentCustomer;
            }
            else {

                System.out.println("Please enter your email");
                String email = scanner.nextLine();

                if(customers.doesEmailAlreadyExist(email))
                    System.out.println("That email already exists");
                else {

                    System.out.println("Please enter your name");
                    String name = scanner.nextLine();

                    currentCustomer = new Customer(name, email);
                    customers.addCustomer(currentCustomer);
                    return currentCustomer;
                }
            }
        }
    }

    public Room findAvailableRoom(Customer customer) {

        while(true) {

            System.out.println("Would you like a [S]tandard room, a [D]eluxe room, or a [P]enthouse room? Type E to exit.");

            String input = scanner.nextLine();

            checkForExit(input);

            if(!input.matches(roomSelectionRegex)) {

                System.out.println("That is not a valid option. Please try again");
                continue;
            }


            System.out.println("Please enter your check in date in the format YYYY/MM/DD");

            LocalDate checkIn = convertDate(scanner.nextLine());

            if(checkIn == null) {

                System.out.println("Invalid date entered.");
                continue;
            }

            System.out.println("Please enter your check out date in the format YYYY/MM/DD");

            LocalDate checkOut = convertDate(scanner.nextLine());

            if(checkOut == null) {

                System.out.println("Invalid date entered.");
                continue;
            }

            Room room = null;
            List<LocalDate> dates = datesToList(checkIn, checkOut);

            switch (input) {

                case "S":
                    room = checkAvailability(standardRooms, dates);
                    break;
                case "D":
                    room = checkAvailability(deluxeRooms, dates);
                    break;
                case "P":
                    room = checkAvailability(penthouseRooms, dates);
                    break;
            }

            if(room==null) {

                System.out.println("I'm sorry, the room you requested is not available for those dates. Please try again");
                continue;
            }

            System.out.println("The room you requested is available for those dates.");

            Charge charge = new Charge(room, Days.daysBetween(checkIn, checkOut).getDays());

            System.out.println("The charge for this room before tax is " + charge.getCostBeforeTax());
            System.out.println("The charge for this room after tax is " + charge.getCostAfterTax());

            System.out.println("To return to the main screen, type M. To continue with this booking, enter any other key.");

            input = scanner.nextLine();

            if(input.equals("M"))
                continue;

            Booking booking = new Booking(charge, room, dates);

            return room;
        }
    }

    private Room checkAvailability(List<? extends Room> roomList, List<LocalDate> dates) {

        for(Room room : roomList) {

            boolean isAvailable = room.checkAvailabilityForDate(dates);

            if(isAvailable)
                return room;
        }

        return null;
    }
}
