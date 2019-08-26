/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import Models.Appointment;
import Models.Customer;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.CustomerConverter;
import util.DBConnection;
import util.TimeConverter;

/**
 * FXML Controller class
 *
 * @author malic
 */
public class AppointmentsController implements Initializable {
    @FXML
    private Label userNameLabel;
    @FXML
    private ComboBox customerComboBox;
    @FXML
    private TextField titleTxtBox;
    @FXML
    private TextField descriptionTxtBox;
    @FXML
    private TextField locationTxtBox;
    @FXML
    private TextField contactTxtBox;
    @FXML
    private TextField typeTxtBox;
    @FXML
    private TextField urlTxtBox;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private ChoiceBox startTimeChoiceBox;
    @FXML
    private ChoiceBox endTimeChoiceBox;
    @FXML
    private Button editButton;
    @FXML
    private Button submitButton;
    @FXML
    private Button deleteBtn;
    @FXML
    private TableView apptTableView;
    @FXML
    private TableColumn<Appointment, ZonedDateTime> dateTableColumn;
    @FXML
    private TableColumn<Appointment, String> typeTableColumn;
    @FXML
    private TableColumn<Appointment, String> descriptionTableColumn;
    private String userName;
    private ArrayList<Appointment> apptList = new ArrayList<>();
    private ObservableList<Appointment> obsApptList= FXCollections.observableList(apptList);
    private boolean edit = false;
    private int userId;
    private int apptId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        userNameLabel.setText("User: " + userName);
        populateTimes();
        populateCustomers();
    }    
    
    @FXML
    public void customerSelect() {
        Customer cust = (Customer) customerComboBox.getSelectionModel().getSelectedItem();
        populateApptTable(cust.getCustomerID());
    }
    
    @FXML
    public void editAppt() {
        if (edit == false) {
            
            
        } else {
            edit = false;
            apptId = -1;
            editButton.setText("Edit");
        }
    }
    
    @FXML
    public void submitAppt() {
        Appointment appt = checkFields();
        String apptValues = appt.getCustomerID() + ", " + appt.getUserID() + ", '"
                + appt.getTitle() + "', '" + appt.getDescription() + "', '" + appt.getLocation() +
                "', '" + appt.getContact() + "', '" + appt.getType() + "', '" + appt.getURL() +
                "', '" + TimeConverter.getDateTimeString(appt.getStart()) + "', '" + TimeConverter.getDateTimeString(appt.getEnd())
                + "', CURRENT_TIMESTAMP, '" + userName + "', CURRENT_TIMESTAMP, '" + userName +"')";
        if (appt != null) {
            if (edit == true) {
                
            } else {
                try {
                    apptValues = "(" + apptValues;
                    DBConnection.connect();
                    DBConnection.insert("appointment (customerId, userId, title, description, "
                            + "location, contact, type, url, start, end, createDate, createdBy, "
                            + "lastUpdate, lastUpdateBy) ", apptValues);
                } catch (SQLException e) {
                    System.out.println("problem with connection");
                    e.printStackTrace();
                } finally {
                    DBConnection.closeConn();
                }
            }
        }
    }
    
    public Appointment checkFields() {
        if (customerComboBox.getSelectionModel().getSelectedItem() == null) {
            return null;
        }
        Customer cust = (Customer) customerComboBox.getSelectionModel().getSelectedItem();
        int custId = cust.getCustomerID();
        String title = titleTxtBox.getText();
        String desc = descriptionTxtBox.getText();
        String loc = locationTxtBox.getText();
        String contact = contactTxtBox.getText();
        String type = typeTxtBox.getText();
        String url = urlTxtBox.getText();
        LocalDate date = dateDatePicker.getValue();
        String start = (String) startTimeChoiceBox.getValue();
        String end = (String) endTimeChoiceBox.getValue();
        
        if (date != null && start != null && end != null && !type.equals("")) {
            if (title.equals("")) {
                title = "Not needed";
            }
            if (desc.equals("")) {
                desc = "Not needed";
            }
            if (loc.equals("")) {
                loc = "Not needed";
            }
            if (contact.equals("")) {
                contact = "Not needed";
            }
            if (url.equals("")) {
                url = "Not needed";
            }
            Appointment appt = new Appointment(custId, userId, title, desc, loc, contact,
                type, url, getUTC(date, start), getUTC(date, end));
            return appt;
        } else {
            return null;
        }   
    }
    
    public ZonedDateTime getUTC(LocalDate date, String time) {
        String period = time.split(" ", 2)[1];
        int hour = Integer.parseInt(time.split(" ", 2)[0].split(":", 2)[0]);
        String minute = time.split(" ", 2)[0].split(":", 2)[1];
        if (period.equals("PM")){
            if (hour == 12) {
                return TimeConverter.getUTCTime(date + " " + hour + ":" + minute);
            } else {
                hour += 12;
                return TimeConverter.getUTCTime(date + " " + hour + ":" + minute);
            }
        } else {
            return TimeConverter.getUTCTime(date + " " + hour + ":" + minute);
        }
    }
    
    @FXML
    public void deleteAppt() {
        if (apptTableView.getSelectionModel().getSelectedItem() != null){
            Appointment appt = (Appointment) apptTableView.getSelectionModel().getSelectedItem();
            try {
                DBConnection.connect();
                DBConnection.delete("customer", "appointmentId=" + appt.getAppointmentID());
            } catch (SQLException e) {
                System.out.println("failed to delete appointment");
            } finally {
                DBConnection.closeConn();
            }
            customerSelect();
        }
    }
    
    public void getUserName(String uName, int uId) {
        userName = uName;
        userId = uId;
        userNameLabel.setText("User: " + userName);
    }
    
    public void populateTimes() {
        ArrayList<String> times = new ArrayList<>();
        for (int x = 7; x < 13; x++) {
            String minutes;
            String hours;
            String period;
            hours = Integer.toString(x) + ":";
            for (int y = 0; y < 59; y += 15) {
                if (y == 0){
                    minutes = Integer.toString(y) + "0";
                } else {
                    minutes = Integer.toString(y);
                }
                if (x == 12) {
                    period = " PM";
                } else {
                    period = " AM";
                }
                times.add(hours + minutes + period);
            }
        }
        for (int x = 1; x < 5; x++) {
            for (int y = 0; y < 59; y += 15) {
                if (y == 0) {
                    times.add(Integer.toString(x) + ":" + Integer.toString(y) + "0 PM");
                } else {
                    times.add(Integer.toString(x) + ":" + Integer.toString(y) + " PM");
                }
                
            }
        }
        for (String time : times) {
            startTimeChoiceBox.getItems().add(time);
            endTimeChoiceBox.getItems().add(time);
        }
    }
    
    public void populateCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            DBConnection.connect();
            ResultSet rs = DBConnection.query("*", "customer");
            while (rs.next()) {
                int customerId = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                customers.add(new Customer(customerId, customerName));
            }
            customerComboBox.setConverter(new CustomerConverter());
            customerComboBox.setItems(observableList(customers));
        } catch (SQLException e) {
            System.out.println("failed to populate customer box");
        } finally {
            DBConnection.closeConn();
        }
    }
    
    
    public void populateApptTable(int CustomerId) {
        apptTableView.getItems().clear();
        try {
            DBConnection.connect();
            ResultSet rs = DBConnection.query("*", "appointment", "customerId=" + CustomerId);
            while (rs.next()) {
                int apptId = rs.getInt("appointmentId");
                int customerId = rs.getInt("customerId");
                int userId = rs.getInt("userId");
                String title = rs.getString("title");
                String description = rs.getString("description");
                String location = rs.getString("location");
                String contact = rs.getString("contact");
                String type = rs.getString("type");
                String url = rs.getString("url");
                ZonedDateTime start = TimeConverter.getLocalTime(rs.getString("start"));
                ZonedDateTime end = TimeConverter.getLocalTime(rs.getString("end"));
                obsApptList.add(new Appointment(apptId, customerId, userId, title, description, location, contact, type, url, start, end));
            }
            dateTableColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
            typeTableColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
            descriptionTableColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
            apptTableView.setItems(obsApptList);
        } catch (SQLException e) {
            System.out.println("failed to populate table");
        } finally {
            DBConnection.closeConn();
        } 
    }
}
