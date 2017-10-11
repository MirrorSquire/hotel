package customer;

import java.util.HashMap;

public class CustomerList {

    HashMap<String, Customer> customers = new HashMap();

    public void addCustomer(Customer c){

        customers.put(c.getEmail(), c);
    }

    public void removeCustomer(Customer c){

        customers.remove(c.getEmail());
    }

    public Customer findCustomer(String email) {

        return customers.get(email);
    }

    public boolean doesEmailAlreadyExist(String email) {

        for(String key : customers.keySet()) {

            if(key.equals(email))
                return true;
        }

        return false;
    }
}
