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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class GameInstructionController implements Initializable {
    
    @FXML private Text instructionText;
    @FXML private AnchorPane anchor;
    
    Student currentStudent;
    String subjectType;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void setCurrentStudent(Student student){
        this.currentStudent = student;
    }
    
    public void changeInstructionText(String text){
        instructionText.setText(text);
    }
    
    @FXML public void handlePlayBttn(ActionEvent event) throws IOException {
        if(subjectType.equals("math")) {
            FXMLLoader mathLoader = new FXMLLoader(getClass().getResource("/View/MathGameView.fxml"));
            Parent mathRoot = (Parent) mathLoader.load();
            MathGameController math = mathLoader.getController();
            math.setCurrentStudent(currentStudent);
            anchor.getChildren().clear();
            anchor.getChildren().addAll(mathRoot);
        } else if(subjectType.equals("science")){
            FXMLLoader scienceLoader = new FXMLLoader(getClass().getResource("/View/ScienceGameView.fxml"));
            Parent scienceRoot = (Parent) scienceLoader.load();
            ScienceGameController science = scienceLoader.getController();
            science.setCurrentStudent(currentStudent);
            anchor.getChildren().clear();
            anchor.getChildren().addAll(scienceRoot);
//            FXMLLoader spaceLoader = new FXMLLoader(getClass().getResource("/View/SpaceshipGameView.fxml"));
//            Parent spaceRoot = (Parent) spaceLoader.load();
//            anchor.getChildren().clear();
//            anchor.getChildren().addAll(spaceRoot);
        }
    }
    
    public void gameSubject(String subject){
        this.subjectType = subject;
    }
}
