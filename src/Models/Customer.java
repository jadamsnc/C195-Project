/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.util.Calendar;

/**
 *
 * @author malic
 */
public class Customer {
    private int CustomerID;
    private String CustomerName;
    private int AddressID;
    private int Active;
    
    public Customer (int customerID, String customerName, int addressID, int active){
        CustomerID = customerID;
        CustomerName = customerName;
        AddressID = addressID;
        Active = active;
    }
    
    public int getCustomerID () {
        return CustomerID;
    }
    
    public String getCustomerName() {
        return CustomerName;
    }
    
    public int getAddressID() {
        return AddressID;
    }
    
    public int getActive() {
        return Active;
    }
}
