/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import Model.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class ProfileController implements Initializable {

    @FXML Button currentAvatar;
    @FXML Button sticker2, sticker4, sticker5, sticker6;
    @FXML Text sticker2Text, sticker4Text, sticker5Text, sticker6Text;
    
    Student currentStudent;
    int stickerValue;
    Model model;
    
    Object ob;
    JSONArray studentArr = new JSONArray();
    JSONObject studentObj = new JSONObject();
    JSONObject updatedStudentObj;
    
  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        JSONParser jp = new JSONParser();
        try{
            FileReader reader = new FileReader("student.json");
            ob = jp.parse(reader);
            studentArr = (JSONArray)ob;
           reader.close();
        } catch(Exception e) {
            System.out.println("Something went wrong");
        }
        

    }
    
     @FXML public void handleStickerBttn(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        stickerValue = Integer.parseInt(value);
    }
     
    @FXML public void handleChangeBttn(ActionEvent event) {
        System.out.println(stickerValue);
        currentStudent.setSticker(stickerValue);
        
         switch(currentStudent.getSticker()) {
            case 1:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/images/bigavatar.png\")");
                break;
            case 2:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/alien.png\")");
                break;
            case 3:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/astronaut.png\")");
                break;
            case 4:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/kitty.png\")");
                break;    
            case 5:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/pinapple.png\")");
                break;    
            case 6:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/unicorn.png\")");
                break;
        }
        updatedStudentObj = this.getStudentObject();
        updatedStudentObj.put("sticker id", currentStudent.getSticker());
        studentArr.remove(this.getStudentObject());
        studentArr.add(updatedStudentObj);
        
        try{
        FileWriter file = new FileWriter("student.json");
        file.write(studentArr.toJSONString());
        file.close();
        }catch(Exception e) {
            System.out.println("Something went wrong");
        }   
    }

    public void setCurrentStudent(Student student){
        this.currentStudent = student;
        
        switch(currentStudent.getSticker()) {
            case 1:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/images/bigavatar.png\")");
                break;
            case 2:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/alien.png\")");
                break;
            case 3:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/astronaut.png\")");
                break;
            case 4:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/kitty.png\")");
                break;    
            case 5:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/pinapple.png\")");
                break;    
            case 6:
                currentAvatar.setStyle("-fx-background-image: url(\"/View/stickerpack/unicorn.png\")");
                break;
        }
    }
    
    public JSONObject getStudentObject(){
        JSONObject currentStudentJSON = new JSONObject();
        for (int i = 0; i<studentArr.size();i++) {
            studentObj = (JSONObject) studentArr.get(i);
            if(studentObj.get("username").equals(currentStudent.getUsername())) {
                currentStudentJSON = studentObj;
                break;
            }
        }
        return currentStudentJSON;
    } 
    
    public void stickerAvailable(int score){
        sticker2.setDisable(true);
        sticker4.setDisable(true);
        sticker5.setDisable(true);
        sticker6.setDisable(true);
       
        
        if(score >= 5) {
           sticker2.setDisable(false);
            sticker2Text.setText("");
        }
        if(score >= 10) {
            sticker4.setDisable(false);
            sticker4Text.setText("");
        }
        if(score >= 20) {
            sticker5.setDisable(false);
            sticker5Text.setText("");
        }
        if(score >= 40) {
            sticker6.setDisable(false);
            sticker6Text.setText("");
        } 
    }
    
}
