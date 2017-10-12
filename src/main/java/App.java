package hotel;

import bookings.BookingProcess;
import customer.Customer;

public class App {

    public static void main(String[] args) {

        while(true) {
            Customer customer = BookingProcess.login();
            BookingProcess.bookRoom(customer);
            break;
        }
    }
}
