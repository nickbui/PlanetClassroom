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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;



/**
 * FXML Controller class
 *
 * @author Nicholas
 */


public class DashboardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private Text welcomeText;
    Student currentStudent;
    @FXML private MenuButton avatarBttn;
    @FXML private Text scoreText;
    @FXML private ImageView spaceImage;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setCurrentStudent(Student student) {
        this.currentStudent = student;
        welcomeText.setText("Hello and Welcome " + currentStudent.getFirstName() + "!");
        scoreText.setText(Integer.toString(currentStudent.getScore()));
        
        switch(currentStudent.getSticker()) {
            case 1:
                avatarBttn.setStyle("-fx-background-image: url(\"/View/images/avatar.png\")");
                break;
            case 2:
                avatarBttn.setStyle("-fx-background-image: url(\"/View/stickerpack/monstersmall.png\")");
                break;
            case 3:
                avatarBttn.setStyle("-fx-background-image: url(\"/View/stickerpack/astronautsmall.png\")");
                break;
            case 4:
                avatarBttn.setStyle("-fx-background-image: url(\"/View/stickerpack/kittysmall.png\")");
                break;    
            case 5:
                avatarBttn.setStyle("-fx-background-image: url(\"/View/stickerpack/pinapplesmall.png\")");
                break;    
            case 6:
                avatarBttn.setStyle("-fx-background-image: url(\"/View/stickerpack/unicornsmall.png\")");
                break;
        }
    }
    
     @FXML public void handleLogOutBttn(ActionEvent event) throws IOException{
        Platform.exit();
     }
     

     
}
