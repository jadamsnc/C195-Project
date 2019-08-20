/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import Models.Customer;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author malic
 */
public class CustomersController implements Initializable {
    
    @FXML
    TextField newNameTxtBox;
    @FXML
    TextField newAddressTxtBox;
    @FXML
    TextField newAddress2TxtBox;
    @FXML
    TextField newPhoneTxtBox;
    @FXML
    TextField newPostalCodeTxtBox;
    @FXML
    ChoiceBox newCityChoiceBox;
    @FXML
    ChoiceBox newCountryChoiceBox;
    @FXML
    CheckBox newActiveChkBox;
    @FXML
    Button addCustomerBtn;
    @FXML
    TextField updateIDTxtBox;
    @FXML
    TextField updateNameTxtBox;
    @FXML
    TextField updateAddressTxtBox;
    @FXML
    TextField updateAddress2TxtBox;
    @FXML
    TextField updatePhoneTxtBox;
    @FXML
    TextField updatePostalCodeTxtBox;
    @FXML
    ChoiceBox updateCityChoiceBox;
    @FXML
    ChoiceBox updateCountryChoiceBox;
    @FXML
    CheckBox updateActiveChkBox;
    @FXML
    Button updateCustomerBtn;
    @FXML
    Button deleteCustomerBtn;
    @FXML
    TableView customerTable;
    @FXML
    TableColumn<Customer, Integer> customerIDCol;
    @FXML
    TableColumn<Customer, String> customerNameCol;
    
    ArrayList<Customer> CustomerList = new ArrayList<>();
    ObservableList<Customer> observableCustomer = FXCollections.observableList(CustomerList);
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            DBConnection.connect();
            ResultSet rs = DBConnection.query("*", "customer");
            while (rs.next()){
                int customerID = rs.getInt("customerId");
                String customerName = rs.getString("customerName");
                int addressId = rs.getInt("addressId");
                int active = rs.getInt("active");
                // Calendar createDate = Calendar.getInstance();
                // createDate.setTime(rs.getDate("createDate"));
                // String createdBy = rs.getString("createdBy");
                observableCustomer.add(new Customer(customerID, customerName, addressId, active));
            }
        } catch (SQLException e) {
            System.out.println("DB failed");
            e.printStackTrace();
        } finally {
            DBConnection.closeConn();
        }
        for (Iterator<Customer> it = observableCustomer.iterator(); it.hasNext();) {
            Customer cust = it.next();
            System.out.println(cust.getCustomerID());
            System.out.println(cust.getCustomerName());
        } 
        customerIDCol.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CustomerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerName"));
        customerTable.setItems(observableCustomer);
    }    
    
}
