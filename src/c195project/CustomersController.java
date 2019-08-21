/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import Models.City;
import Models.Country;
import Models.Customer;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import util.CityConverter;
import util.CountryConverter;
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
    ComboBox newCityComboBox;
    @FXML
    ComboBox newCountryComboBox;
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
    ComboBox updateCityComboBox;
    @FXML
    ComboBox updateCountryComboBox;
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
        populateTable();
        populateCountry();
    }
    
    // this needs to be updated to get the user that is logged in instead of just using test
    @FXML
    public void addCustomerBtnHandler() {
        String name = newNameTxtBox.getText();
        String Address = newAddressTxtBox.getText();
        String Address2 = newAddress2TxtBox.getText();
        String Phone = newPhoneTxtBox.getText();
        String PostalCode = newPostalCodeTxtBox.getText();
        City city = (City) newCityComboBox.getValue();
        Country country = (Country) newCountryComboBox.getValue();
        int active;
        if (newActiveChkBox.isSelected()) {
            active = 1;
        } else {
            active = 0;
        }
        
        String addressValues = "('" + Address + "','" + Address2 + "'," + city.getCityID() +
                ",'" + PostalCode + "','" + Phone + "', CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test')";
        if (name != null && Address != null && Phone != null && PostalCode != null
                && city != null && country != null) {
            
            try {
                DBConnection.insert("address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) ",
                        addressValues);
                ResultSet rs = DBConnection.query("*","address", "address='" +Address + "'");
                rs.next();
                int AddressID = rs.getInt("addressId");
                String customerValues = "('" + name + "'," + AddressID + "," + active + ", CURRENT_TIMESTAMP, 'test', CURRENT_TIMESTAMP, 'test')";
                DBConnection.insert("customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy)", customerValues);
                        
                
            } catch (SQLException e) {
                System.out.println("Problem with DB insert");
                e.printStackTrace();
            } finally {
                DBConnection.closeConn();
            }
        }
    }
    
    @FXML
    public void updateCustomerBtnHandler() {
        
    }
    
    @FXML
    public void deleteCustomerBtnHandler() {
        if (customerTable.getSelectionModel().getSelectedItem() != null) {
            Customer customer = (Customer) customerTable.getSelectionModel().getSelectedItem();
            try {
                DBConnection.delete("customer", "customerId=" + customer.getCustomerID() + ";");
                DBConnection.delete("address", "addressId=" + customer.getAddressID());
            } catch (SQLException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Problem deleting from database");
                alert.setContentText("Sorry, the customer could not be deleted");
                alert.showAndWait();
            } finally {
                DBConnection.closeConn();
            }
        }
    }
    
    @FXML
    public void newCountryComboBoxHandler() {
        if (newCountryComboBox.getSelectionModel().getSelectedItem() != null) {
            Country selectedCountry = (Country) 
                    newCountryComboBox.getSelectionModel().getSelectedItem();
            populateCity(selectedCountry.getCountryID(), newCityComboBox);
        }
    }
    
    @FXML
    public void updateCountryComboBoxHandler() {
        if (updateCountryComboBox.getSelectionModel().getSelectedItem() != null) {
            Country selectedCountry = (Country) 
                    updateCountryComboBox.getSelectionModel().getSelectedItem();
            populateCity(selectedCountry.getCountryID(), updateCityComboBox);
        }
    }
    
    // this function will be used to populate the country box on initialization
    public void populateCountry(){
        ArrayList<Country> countryList = new ArrayList();
        try {
            ResultSet rs = DBConnection.query("*", "country");
            while (rs.next()) {
                int CountryID = rs.getInt("countryId");
                String CountryName = rs.getString("country");
                countryList.add(new Country(CountryID, CountryName));
            }
            newCountryComboBox.setConverter(new CountryConverter());
            newCountryComboBox.setItems(observableList(countryList));
            updateCountryComboBox.setConverter(new CountryConverter());
            updateCountryComboBox.setItems(observableList(countryList));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Problem retrieving city list");
            alert.setContentText("Sorry, the city list was unable to be retrieved");
            alert.showAndWait();
        } finally {
            DBConnection.closeConn();
        }
    }
    
    // this function will be used to populate the city box when country is selected
    public void populateCity(int countryID, ComboBox box){
        ArrayList<City> cityList = new ArrayList();
        try {
            ResultSet rs = DBConnection.query("*", "city", "countryId='" + countryID + "'");
            while (rs.next()) {
                int CityID = rs.getInt("cityId");
                String CityName = rs.getString("city");
                cityList.add(new City(CityID, CityName));
            }
            box.setConverter(new CityConverter());
            box.setItems(observableList(cityList));
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR); 
            alert.setTitle("Error");
            alert.setHeaderText("Problem retrieving country list");
            alert.setContentText("Sorry, the country list was unable to be retrieved");
            alert.showAndWait();
        } finally {
            DBConnection.closeConn();
        }
    }
    
    public void populateTable() {
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
        } finally {
            DBConnection.closeConn();
        }
        observableCustomer.stream().map((cust) -> {
            System.out.println(cust.getCustomerID());
            return cust;
        }).forEachOrdered((Customer cust) -> {
            System.out.println(cust.getCustomerName());
        }); 
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("CustomerName"));
        customerTable.setItems(observableCustomer);
    }
}
