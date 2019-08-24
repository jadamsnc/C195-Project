/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import Models.Customer;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import static javafx.collections.FXCollections.observableList;
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
import util.CustomerConverter;
import util.DBConnection;

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
    private TableColumn dateTableColumn;
    @FXML
    private TableColumn timeTableColumn;
    @FXML
    private TableColumn descriptionTableColumn;
    private String userName;

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
        
    }
    
    @FXML
    public void editAppt() {
        
    }
    
    @FXML
    public void submitAppt() {
        
    }
    
    @FXML
    public void deleteAppt() {
        
    }
    
    public void getUserName(String uName) {
        userName = uName;
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
                
            }
        } catch (SQLException e) {
            
        } finally {
            DBConnection.closeConn();
        }
    }
}
