/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

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
        Parent mathRoot;
        mathRoot = FXMLLoader.load(getClass().getResource("/View/MathGameView.fxml"));
        gameAnchor.getChildren().clear();
        gameAnchor.getChildren().addAll(mathRoot);
    }
}
