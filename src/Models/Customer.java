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
        setCustomerID(customerID);
        setCustomerName(customerName);
        setAddressID(addressID);
        setActive(active);
    }
    
    public Customer(int customerID, String customerName) {
        setCustomerID(customerID);
        setCustomerName(customerName);
    }
    
    public int getCustomerID () {
        return CustomerID;
    }
    
    public void setCustomerID (int custid) {
        CustomerID = custid;
    }
    
    public String getCustomerName() {
        return CustomerName;
    }
    
    public void setCustomerName(String name) {
        CustomerName = name;
    }
    
    public int getAddressID() {
        return AddressID;
    }
    
    public void setAddressID(int addID) {
        AddressID = addID;
    }
    
    public int getActive() {
        return Active;
    }
    
    public void setActive(int active) {
        Active = active;
    }
}
