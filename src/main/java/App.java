import charges.Charge;
import customer.Booking;
import customer.Customer;
import hotel.Hotel;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import rooms.Room;
import utils.Utils;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String NEW_KEYWORD = "new";

    private static final String ROOM_SELECTION_REGEX = "[SDP]";

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        while (true) {

            System.out.println("Welcome to hotel");
            Customer currentCustomer;

            System.out.println("If you are a returning customer, please enter your ID number. Otherwise, please type \"" + NEW_KEYWORD+"\" to create an account. Type E to exit");

            String input = scanner.nextLine();

            if(input.equals("E"))
                break;

            if (!input.equals(NEW_KEYWORD)) {

                currentCustomer = hotel.login(input);

                if(currentCustomer == null) {

                    System.out.println("Invalid login.");
                    continue;
                }
            }
            else {

                System.out.println("Please enter your email");
                String email = scanner.nextLine();

                if (hotel.getCustomers().doesEmailAlreadyExist(email)) {

                    System.out.println("That email already exists");
                    continue;
                } else {

                    System.out.println("Please enter your name");

                    String name = scanner.nextLine();

                    currentCustomer = new Customer(name, email);
                    hotel.getCustomers().addCustomer(currentCustomer);
                }
            }

            System.out.println("Would you like a [S]tandard room, a [D]eluxe room, or a [P]enthouse room? Type E to exit.");

            input = scanner.nextLine();

            if(input.equals("E"))
                break;

            if(!input.matches(ROOM_SELECTION_REGEX)) {

                System.out.println("That is not a valid option. Please try again");
                continue;
            }


            System.out.println("Please enter your check in date in the format YYYY/MM/DD");

            LocalDate checkIn = Utils.convertDate(scanner.nextLine());

            if(checkIn == null) {

                System.out.println("Invalid date entered.");
                continue;
            }

            System.out.println("Please enter your check out date in the format YYYY/MM/DD");

            LocalDate checkOut = Utils.convertDate(scanner.nextLine());

            if(checkOut == null) {

                System.out.println("Invalid date entered.");
                continue;
            }

            Optional<? extends Room> room = Optional.empty();
            List<LocalDate> dates = Utils.datesToList(checkIn, checkOut);

            switch (input) {

                case "S":
                    room = hotel.getStandardRooms().stream()
                            .filter(aaa(dates))
                            .findFirst();
                    break;
                case "D":
                    room = hotel.getDeluxeRooms().stream()
                            .filter(aaa(dates))
                            .findFirst();
                    break;
                case "P":
                    room = hotel.getPenthouseRooms().stream()
                            .filter(aaa(dates))
                            .findFirst();
                    break;
                default:
                    break;
            }

            if(!room.isPresent()) {

                System.out.println("I'm sorry, the room you requested is not available for those dates. Please try again");
                continue;
            }

            System.out.println("The room you requested is available for those dates.");

            Charge charge = new Charge(room.get(), Days.daysBetween(checkIn, checkOut).getDays());

            System.out.println("The charge for this room before tax is " + charge.getCostBeforeTax());
            System.out.println("The charge for this room after tax is " + charge.getCostAfterTax());

            System.out.println("To return to the main screen, type M. To continue with this booking, enter any other key.");

            input = scanner.nextLine();

            if(input.equals("M"))
                continue;

            Booking booking = new Booking(charge, room.get(), dates);
            currentCustomer.addToBookings(booking);
            room.get().addToBookings(booking);
        }
    }

    private static Predicate<Room> aaa(List<LocalDate> dates) {

        return room -> room.checkAvailabilityForDate(dates);
    }
}
