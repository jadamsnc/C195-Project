/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Models.Customer;
import javafx.util.StringConverter;

/**
 *
 * @author malic
 */
public class CustomerConverter extends StringConverter<Customer>{
    @Override
    public Customer fromString(String customerAsString) {
        String[] parts = customerAsString.split(" ");
        return new Customer(Integer.parseInt(parts[0]), parts[1]);
    }
    
    @Override
    public String toString(Customer customer) {
        return customer.getCustomerID() + " " + customer.getCustomerName();
    }
}
