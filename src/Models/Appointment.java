/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author jeremy
 */
public class Appointment {
    private int AppointmentID;
    private int CustomerID;
    private int UserID;
    private String Title;
    private String Description;
    private String Location;
    private String Contact;
    private String Type;
    private String URL;
    
    
    public Appointment(int appointmentID, int custId, int userId, String title, String description,
            String location, String contact, String type, String url) {
        setAppointmentID(appointmentID);
        setCustomerID(custId);
        setUserID(userId);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setContact(contact);
        setType(type);
        setURL(url);
    }
    
    public Appointment(int custId, int userId, String title, String description,
            String location, String contact, String type, String url) {
        setCustomerID(custId);
        setUserID(userId);
        setTitle(title);
        setDescription(description);
        setLocation(location);
        setContact(contact);
        setType(type);
        setURL(url);
    }
    
    public int getAppointmentID() {
        return AppointmentID;
    }
    
    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }
    
    public int getCustomerID() {
        return CustomerID;
    }
    
    public void setCustomerID(int custId){
        CustomerID = custId;
    }
    
    public int getUserID() {
        return UserID;
    }
    
    public void setUserID(int userId) {
        UserID = userId;
    }
    
    public String getTitle() {
        return Title;
    }
    
    public void setTitle(String title) {
        Title = title;
    }
    
    public String getDescription() {
        return Description;
    }
    
    public void setDescription(String description) {
        Description = description;
    }
    
    public String getLocation() {
        return Location;
    }
    
    public void setLocation(String location) {
        Location = location;
    }
    
    public String getContact() {
        return Contact;
    }
    
    public void setContact(String contact) {
        Contact = contact;
    }
    
    public String getType() {
        return Type;
    }
    
    public void setType(String type) {
        Type = type;
    }
    
    public String getURL() {
        return URL;
    }
    
    public void setURL(String url) {
        URL = url;
    }
}
