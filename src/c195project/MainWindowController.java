/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package c195project;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author malic
 */
public class MainWindowController implements Initializable{
    
    @FXML
    Button customersBtn;
    @FXML
    Button appointmentsBtn;
    @FXML
    Button calendarBtn;
    @FXML
    Button reportsBtn;
    @FXML
    Button logFileBtn;
    
    @FXML
    void CustomersBtnClick (ActionEvent event) {
        NewFXWindow("c195project/Customers.fxml", "Customers");
    }
    
    @FXML
    void AppointmentsBtnClick (ActionEvent event) {
        NewFXWindow("c195project/Appointments.fxml", "Appointments");
    }
    
    @FXML
    void CalendarBtnClick (ActionEvent event) {
        NewFXWindow("c195project/Calendar.fxml", "Calendar");
    }
    
    @FXML
    void ReportsBtnClick (ActionEvent event) {
        NewFXWindow("c195project/Reports.fxml", "Reports");
    }
    
    @FXML
    void LogFileBtnClick (ActionEvent event) {
        try {
            // Open text editor with log file, will fail on anything before windows XP
            Desktop.getDesktop().edit(new File("LoginRecord.txt"));
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to open text file");
            alert.setContentText("Sorry the text editor failed to open");
            alert.showAndWait();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    
    // class to load JavaFX windows to avoid duplicating code
    private void NewFXWindow(String path, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(path));
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load window");
            alert.setContentText("Sorry, the window has failed to load");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    
}
