/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;


/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class GamesController implements Initializable {
    
    Student currentStudent;
    /**
     * Initializes the controller class.
     */
    
    @FXML private AnchorPane gameAnchor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    
    @FXML
    public void handleMathGameBttn(ActionEvent event) throws IOException {
       FXMLLoader instructionLoader = new FXMLLoader(getClass().getResource("/View/GameInstructionView.fxml"));
       Parent instructionRoot = (Parent) instructionLoader.load();
       GameInstructionController instruction = instructionLoader.getController();
       instruction.setCurrentStudent(currentStudent);
       instruction.changeInstructionText("The goal of the game is try to get the number 24 by adding,multiplying, subtracting, or dividing between the 4 numbers");
       instruction.gameSubject("math");
       gameAnchor.getChildren().clear();
       gameAnchor.getChildren().addAll(instructionRoot);
    }
    
    @FXML
    public void handleScienceGameBttn(ActionEvent event) throws IOException {
        FXMLLoader instructionLoader = new FXMLLoader(getClass().getResource("/View/GameInstructionView.fxml"));
       Parent instructionRoot = (Parent) instructionLoader.load();
       GameInstructionController instruction = instructionLoader.getController();
       instruction.setCurrentStudent(currentStudent);
       instruction.changeInstructionText("You will be given 10 science related questions, if you answer 70% of the questions right, you win!");
       instruction.gameSubject("science");
       gameAnchor.getChildren().clear();
       gameAnchor.getChildren().addAll(instructionRoot);
    }
    
     public void setCurrentStudent(Student student){
         this.currentStudent = student;
    }
}
