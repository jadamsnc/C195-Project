/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import util.DBConnection;

/**
 *
 * @author malic
 */
public class MainWindowController implements Initializable{
    @FXML
    private RadioButton monthlyRadio;
    @FXML
    private RadioButton weeklyRadio;
    @FXML
    private TableView calendarTable;
    
    private ObservableList<ObservableList> appointments;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
