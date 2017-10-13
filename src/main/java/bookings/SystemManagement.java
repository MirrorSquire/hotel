package bookings;

import customer.Customer;
import customer.CustomerList;
import lombok.Getter;

public class SystemManagement {

    @Getter private static boolean isLoggedIn = false;
    @Getter private static boolean exited = false;

    public static void createAccount(){

    }

    public static void logout(){

        isLoggedIn = false;
    }

    public static void exit() {

        System.exit(0);
    }
}
