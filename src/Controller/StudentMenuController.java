/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import Model.Student;
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

    @FXML BorderPane bp;
    @FXML Button dashboardBttn, gamesBttn, profileBttn, settingsBttn;
    Model model;
    

    
    private Student currentStudent;
    
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashboardView.fxml"));
    Parent dashRoot;
    
  

    public StudentMenuController() {
        try {
            this.dashRoot = (Parent) loader.load();
        } catch (IOException ex) {
            Logger.getLogger(StudentMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            bp.setCenter(dashRoot);
            dashboardBttn.setStyle("-fx-background-color: #460ea3");
    }
    
    /**
     * Initializes the controller class.
     * @param event
     * @throws java.io.IOException
     */
    @FXML public void handleDashboardBttn(ActionEvent event) throws IOException{
       FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DashboardView.fxml"));
       Parent dashRoot = (Parent) loader.load();
       DashboardController dash = loader.getController();
       dash.setCurrentStudent(currentStudent);
       bp.setCenter(dashRoot);
       if((Button)event.getSource() == dashboardBttn){
           gamesBttn.setStyle("-fx-background-color: none");
           dashboardBttn.setStyle("-fx-background-color: #460ea3");
           profileBttn.setStyle("-fx-background-color: none");
           settingsBttn.setStyle("-fx-background-color: none");
       }
    }
    
    @FXML public void handleGamesBttn(ActionEvent event) throws IOException{
       FXMLLoader gameLoader = new FXMLLoader(getClass().getResource("/View/GamesView.fxml"));
       Parent gameRoot = (Parent) gameLoader.load();
       GamesController game = gameLoader.getController();
       game.setCurrentStudent(currentStudent);
        bp.setCenter(gameRoot);
        if((Button)event.getSource() == gamesBttn){
            dashboardBttn.setStyle("-fx-background-color: none");
            gamesBttn.setStyle("-fx-background-color: #460ea3");
            profileBttn.setStyle("-fx-background-color: none");
            settingsBttn.setStyle("-fx-background-color: none");
       }
    }
    
    @FXML public void handleProfileBttn(ActionEvent event) throws IOException{
       FXMLLoader profileLoader = new FXMLLoader(getClass().getResource("/View/ProfileView.fxml"));
       Parent profileRoot = (Parent) profileLoader.load();
       ProfileController profile = profileLoader.getController();
       profile.setCurrentStudent(currentStudent);
       profile.stickerAvailable(currentStudent.getScore());
       bp.setCenter(profileRoot);
       
       if((Button)event.getSource() == profileBttn){
           gamesBttn.setStyle("-fx-background-color: none");
           dashboardBttn.setStyle("-fx-background-color: none");
           profileBttn.setStyle("-fx-background-color: #460ea3");
           settingsBttn.setStyle("-fx-background-color: none");
       }
    }
    
     @FXML public void handleSettingsBttn(ActionEvent event) throws IOException{
        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("/View/SettingsView.fxml"));
        Parent root = (Parent) settingsLoader.load();
        SettingsController settings = settingsLoader.getController();
        settings.setCurrentStudent(currentStudent);
        settings.settingTheme(currentStudent.isDarkTheme());
        bp.setCenter(root);

       
       if((Button)event.getSource() == settingsBttn){
           gamesBttn.setStyle("-fx-background-color: none");
           dashboardBttn.setStyle("-fx-background-color: none");
           profileBttn.setStyle("-fx-background-color: none");
           settingsBttn.setStyle("-fx-background-color: #460ea3");
       }
    }
    
    public void setCurrentStudent(Student student){
        this.currentStudent = student;
        DashboardController dash = loader.getController();
        dash.setCurrentStudent(currentStudent);
    }
  
    public void setTheme (boolean theme){
        theme = currentStudent.isDarkTheme();
    }
  
   
}
