/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import util.DBConnection;

/**
 * FXML Controller class
 *
 * @author malic
 */
public class ReportsController implements Initializable {
    
    String userName;
    int userId;
    @FXML
    private Label userNameLabel;
    
    private final String months[] = new String[]{"January", "February", "March", "April", "May", "June",
        "July", "August", "September", "October", "November", "December"};
    private int currentMonth = 0;
    private HashMap<String, Integer> typeMap = new HashMap<>();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        currentMonth = LocalDate.now().getMonthValue() - 1;
        System.out.println(months[currentMonth]);
        
    }    
    
    @FXML
    public void typeMonthSelect() {
        typeMap.clear();
        try {
            DBConnection.connect();
            ResultSet rs = DBConnection.query("*", "appointment", "MONTHNAME(start) = '" + months[currentMonth] + "'");
            while (rs.next()) {
                Integer oldValue = typeMap.get(rs.getString("type"));
                if (oldValue != null) {
                    Integer newValue = oldValue + 1;
                    typeMap.replace(rs.getString("type"), newValue);
                } else {
                    typeMap.put(rs.getString("type"), 1);
                }
            }
            for (HashMap.Entry<String, Integer> entry : typeMap.entrySet()) {
                // need to make is info display here use entry.getKey() and .getValue()
            }
        } catch (SQLException e ) {
            
        } finally {
            DBConnection.closeConn();
        }
    }
    
    public void populateTypeMonth() {
        // need to populate the combo box from month array here
    }
    
    public void getUserName(String uName, int uId) {
        userName = uName;
        userId = uId;
        userNameLabel.setText("User: " + userName);
    }
    
}
