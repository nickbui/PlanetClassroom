/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class SpaceshipGameController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML private ImageView spaceship;
    
    @FXML private ImageView explosion;
    @FXML private AnchorPane spaceAnchor;
    @FXML private Button startBttn;
    
    FXMLLoader spaceLoader = new FXMLLoader(getClass().getResource("/View/SpaceGameMainView.fxml"));
    SpaceGameMainController space = spaceLoader.getController();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        explosion.setVisible(false);
        startBttn.setVisible(false);
        
        Timeline t = new Timeline();
        t.setCycleCount(1);
        
        t.getKeyFrames().add(new KeyFrame(
                Duration.millis(100), (ActionEvent event) ->{
                    spaceship.setY(100);
                }
        ));
         t.getKeyFrames().add(new KeyFrame(
                Duration.millis(200), (ActionEvent event) ->{
                    spaceship.setY(150);
                }
        ));
          t.getKeyFrames().add(new KeyFrame(
                Duration.millis(300), (ActionEvent event) ->{
                    spaceship.setY(200);
                }
        ));
           t.getKeyFrames().add(new KeyFrame(
                Duration.millis(400), (ActionEvent event) ->{
                    spaceship.setY(250);
                }
        ));
            t.getKeyFrames().add(new KeyFrame(
                Duration.millis(500), (ActionEvent event) ->{
                    spaceship.setY(300);
                    spaceship.setVisible(false);
                    explosion.setVisible(true);
                    
                }
        ));
             t.getKeyFrames().add(new KeyFrame(
                Duration.millis(700), (ActionEvent event) ->{
                 explosion.setVisible(false);
                 startBttn.setVisible(true);
                }
        ));
        t.play();
    }

    @FXML
    public void handleStartBttn(ActionEvent event) throws IOException {
       Parent spaceRoot = (Parent) spaceLoader.load(); 
       spaceAnchor.getChildren().clear();
      spaceAnchor.getChildren().addAll(spaceRoot);
    }
    
 
    

}
