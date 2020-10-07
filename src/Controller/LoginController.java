/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import java.awt.Color;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class LoginController{
     Model model;
    
    String userInput;
    String passInput;
    
    @FXML private Text actiontarget;
    @FXML private TextField userField;
    @FXML private TextField passwordField;
   
  

    @FXML public void handleSubmitButtonAction(ActionEvent event) throws IOException {
        model = new Model();
        userInput = userField.getText();
        passInput = passwordField.getText();
        if(model.getuL().authenticate(userInput, passInput)) {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/View/StudentMenuView.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Welcome Planet Classroom");
            stage.setScene(new Scene(root, 894, 503));
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();    
        } else{
            actiontarget.setText("Failed. Please Try Again.");
        }
    }
}
