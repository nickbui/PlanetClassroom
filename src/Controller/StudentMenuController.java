/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class StudentMenuController implements Initializable {

    Parent dashboardRoot;
    @FXML BorderPane bp;
    @FXML Button dashboardBttn, gamesBttn, profileBttn, settingsBttn;
    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     */
    @FXML public void handleDashboardBttn(ActionEvent event) throws IOException{
       dashboardRoot = FXMLLoader.load(getClass().getResource("/View/DashboardView.fxml"));
       bp.setCenter(dashboardRoot);
       if((Button)event.getSource() == dashboardBttn){
           gamesBttn.setStyle("-fx-background-color: none");
           dashboardBttn.setStyle("-fx-background-color: #460ea3");
       }
    }
    
    @FXML public void handleGamesBttn(ActionEvent event) throws IOException{
        Parent gameRoot;
        gameRoot = FXMLLoader.load(getClass().getResource("/View/GamesView.fxml"));
        bp.setCenter(gameRoot);
        if((Button)event.getSource() == gamesBttn){
             dashboardBttn.setStyle("-fx-background-color: none");
           gamesBttn.setStyle("-fx-background-color: #460ea3");
       }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            dashboardRoot = FXMLLoader.load(getClass().getResource("/View/DashboardView.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(StudentMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(dashboardRoot);
        dashboardBttn.setStyle("-fx-background-color: #460ea3");
    }
    
}
