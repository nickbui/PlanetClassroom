/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;


/**
 *
 * @author Nicholas
 */
public class FxmlLoader {
    private Pane view;
    
    public Pane getPage(String fileName) {
        try {
            String address = ("src/View/" + fileName + ".fxml");
            InputStream fileUrl = new FileInputStream(address);
            if(fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }
            
        view = new FXMLLoader().load(fileUrl);
            
        }catch (Exception e){
                    System.out.println("No page " + fileName +" please check FXMLLoader.");
        }
        return view;
    }
    
}
