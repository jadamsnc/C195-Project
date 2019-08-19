/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    TableColumn customerIDCol;
    @FXML
    TableColumn customerNameCol;
    @FXML
    TableColumn customerAddressCol;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
