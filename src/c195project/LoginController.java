/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import util.DBConnection;

/**
 *
 * @author malic
 */
public class LoginController implements Initializable {
    
    @FXML
    private Label nameLbl;
    @FXML
    private Label passLbl;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField passBox;
    @FXML
    private Button loginBtn;
    @FXML
    private Label errorLbl;
    @FXML
    private Label welcomeLbl;
    
    ResourceBundle rb;
    
    // I need to recreate this to use the mysql server for user login
    @FXML
    private void LoginButtonHandler(ActionEvent event) {
        String userName = nameBox.getText();
        String password = passBox.getText();
        if (loginCheck(userName, password)) {
            // clear any error messages on the window and then write the login time
            // to the log file, catch any IO issues with writing to the file and 
            // print the stack trace to stdout
            try {
                errorLbl.setText("");
                System.out.println("success");
                BufferedWriter writer = new BufferedWriter(new FileWriter("LoginRecord.txt", true));
                writer.newLine();
                writer.write("Username: " + userName + " Time: " + LocalDateTime.now());
                writer.close();
            } catch (IOException e) {
                System.out.println("unable to write to file :" + e.getStackTrace() );
            }
            // change scene to the main program window
            // if any error occurs loading the window print error info to stdout
            // throw error message so user isn't confused about nothing happening
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
                Parent root = loader.load();
                MainWindowController controller = loader.getController();
                Scene mainWindowScene = new Scene(root);
                Stage mainWindow = (Stage) ((Node)event.getSource()).getScene().getWindow();
                mainWindow.setTitle("Scheduler");
                mainWindow.setScene(mainWindowScene);
                controller.getUserName(userName);
                mainWindow.show();
            } catch (IOException e) {
                System.out.println("Failed to open main window: " + e.getStackTrace());
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Problem loading main window");
                alert.setContentText("Sorry, the main window has failed to load");
                alert.showAndWait();
            }
        }
        else {
            errorLbl.setText(this.rb.getString("badpass"));
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.rb = rb;
        System.out.println(Locale.getDefault());
        Locale.getDefault();
        loginBtn.setText(rb.getString("login"));
        nameLbl.setText(rb.getString("username"));
        passLbl.setText(rb.getString("password"));
        welcomeLbl.setText(rb.getString("welcome"));
    }    
    
    private boolean loginCheck(String userName, String Password){
        try {
            DBConnection.connect();
            ResultSet rs = DBConnection.query("*", "user", "userName='"+userName +"'");
            while (rs.next()) {
                if (rs.getString("password").equals(Password)) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("database connection failed");
        } finally {
            DBConnection.closeConn();
        }
        return false;
    }
}
