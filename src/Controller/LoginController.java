/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import Model.Model;
import Model.Student;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class LoginController {
    Model model= new Model();
    private String userInput;
    private String passInput;
    
    @FXML private Text actiontarget;
    @FXML private TextField userField;
    @FXML private TextField passwordField;
    
    Student student;
   
    @FXML public void handleSubmitButtonAction(ActionEvent event) throws IOException {
        userInput = userField.getText();
        passInput = passwordField.getText();
        
        if(getModel().getuL().authenticate(getUserInput(), getPassInput())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/StudentMenuView.fxml"));
            Parent root = (Parent) loader.load();
            StudentMenuController studentMenu = loader.getController();
            for(int i =0; i<model.getStudentList().size();i++) {
                if(userInput.equals(model.getStudentList().get(i).getUsername())) {
                    student = model.getStudentList().get(i);
                    break;
                } 
            }
            studentMenu.setCurrentStudent(student);
            Stage stage = new Stage();
            stage.setTitle("Welcome to Planet Classroom");
            Scene scene = new Scene(root, 894,503);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                }
            });
            if(student.isDarkTheme() == true) {
                scene.getStylesheets().add("View/dark-theme.css");
            }
            stage.setScene(scene);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } else{
            actiontarget.setText("Failed. Please Try Again.");
        }
    }
    
    @FXML public void handleSignUpBttn(ActionEvent event) throws IOException {
       FXMLLoader signupLoader = new FXMLLoader(getClass().getResource("/View/SignupView.fxml"));
       Parent signupRoot = (Parent) signupLoader.load();
      
        Scene signUpScene= new Scene(signupRoot);
        Stage stage = new Stage();
        stage.setTitle("Sign Up");
        stage.setScene(signUpScene);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    /**
     * @return the userInput
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * @return the passInput
     */
    public String getPassInput() {
        return passInput;
    }

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }
    
    
}
