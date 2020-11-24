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
import java.io.IOException;
import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class SettingsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private BooleanProperty switchedOn = new SimpleBooleanProperty();
    private TranslateTransition translateAnimation = new TranslateTransition(Duration.seconds(0.25));
    private FillTransition fillAnimation = new FillTransition(Duration.seconds(0.25));
    private ParallelTransition animation = new ParallelTransition(translateAnimation, fillAnimation);
  
    @FXML private Circle trigger;
    @FXML private Rectangle background;
    @FXML private Text themeText, passwordStatus;
    @FXML private AnchorPane settingAnchor;
    @FXML private TextField oldPassword, newPassword;
    
    
    
    public Student currentStudent;
    boolean isOn;
    private boolean darkTheme;
    String oldPass;
    String newPass;
   
    Object ob;
    JSONArray studentArr = new JSONArray();
    JSONObject studentObj = new JSONObject();
    JSONObject updatedStudentObj;
    

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        trigger.setOnMouseClicked(event->{
            switchedOn.set(!switchedOn.get());
        });
       
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

    public BooleanProperty switchedOnProperty(){
        return switchedOn;
    }

   public void setCurrentStudent(Student student){
        this.currentStudent = student;
        switchedOn.setValue(student.isDarkTheme());
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
   
   public void settingTheme(boolean isTheme){
       if(isTheme == true){
          this.trigger.setLayoutX(220);
          background.setFill(Color.LIGHTGREEN);
           translateAnimation.setNode(trigger);
            fillAnimation.setShape(background);
             
            switchedOn.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> obs, Boolean oldState, Boolean newState) {
                    isOn = oldState;
                    translateAnimation.setToX(isOn ? 50 -100: 0);
                    fillAnimation.setFromValue(isOn ? Color.LIGHTGREEN: Color.WHITE);
                    fillAnimation.setToValue(isOn ? Color.WHITE: Color.LIGHTGREEN);
                    animation.play();
                    if(newState){
                       settingAnchor.getScene().getStylesheets().add("View/dark-theme.css"); 
                        System.out.println("Dark Mode");
                        darkTheme = true;
                        currentStudent.setDarkTheme(true);
                        updatedStudentObj = SettingsController.this.getStudentObject();
                        updatedStudentObj.put("dark theme", darkTheme);
                        studentArr.remove(SettingsController.this.getStudentObject());
                        studentArr.add(updatedStudentObj);
                        try{
                            try (FileWriter file = new FileWriter("student.json")) {
                                file.write(studentArr.toJSONString());
                            }
                        }catch(IOException e) {
                            System.out.println("Something went wrong");
                        }
                    } else if (oldState){
                      settingAnchor.getScene().getStylesheets().remove("View/dark-theme.css"); 
                        System.out.println("Light Mode");
                         darkTheme = false;
                        currentStudent.setDarkTheme(darkTheme);
                        updatedStudentObj = SettingsController.this.getStudentObject();
                        updatedStudentObj.put("dark theme", darkTheme);
                        studentArr.remove(SettingsController.this.getStudentObject());
                        studentArr.add(updatedStudentObj);
                        try{
                            FileWriter file = new FileWriter("student.json");
                            file.write(studentArr.toJSONString());
                            file.close();
                        }catch(Exception e) {
                            System.out.println("Something went wrong");
                        }
                    }
               }
                });
              themeText.textProperty().bind(Bindings.when(this.switchedOnProperty()).then("Dark").otherwise("Light"));
       }else if(isTheme == false){
            this.trigger.setLayoutX(170);
            background.setFill(Color.WHITE);
            translateAnimation.setNode(trigger);
            fillAnimation.setShape(background);
        
            switchedOn.addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> obs, Boolean oldState, Boolean newState) {
                    isOn = newState;
                    translateAnimation.setToX(isOn ? 100 -50: 0);
                    fillAnimation.setFromValue(isOn ? Color.WHITE: Color.LIGHTGREEN);
                    fillAnimation.setToValue(isOn ? Color.LIGHTGREEN: Color.WHITE);
                    animation.play();
                    if(newState){
                        settingAnchor.getScene().getStylesheets().add("View/dark-theme.css"); 
                        System.out.println("Dark Mode");
                        darkTheme = true;
                        currentStudent.setDarkTheme(true);
                        updatedStudentObj = SettingsController.this.getStudentObject();
                        updatedStudentObj.put("dark theme", darkTheme);
                        studentArr.remove(SettingsController.this.getStudentObject());
                        studentArr.add(updatedStudentObj);
                        try{
                            try (FileWriter file = new FileWriter("student.json")) {
                                file.write(studentArr.toJSONString());
                            }
                        }catch(IOException e) {
                            System.out.println("Something went wrong");
                        }
                    } else{
                        System.out.println("Light Mode");
                        settingAnchor.getScene().getStylesheets().remove("View/dark-theme.css");
                         darkTheme = false;
                        currentStudent.setDarkTheme(darkTheme);
                        updatedStudentObj = SettingsController.this.getStudentObject();
                        updatedStudentObj.put("dark theme", darkTheme);
                        studentArr.remove(SettingsController.this.getStudentObject());
                        studentArr.add(updatedStudentObj);
                        try{
                            FileWriter file = new FileWriter("student.json");
                            file.write(studentArr.toJSONString());
                            file.close();
                        }catch(Exception e) {
                            System.out.println("Something went wrong");
                        }
                    }
                    }
                               });
       }
   }

    /**
     * @return the darkTheme
     */
    public boolean isDarkTheme() {
        return darkTheme;
    }

    /**
     * @param darkTheme the darkTheme to set
     */
    public void setDarkTheme(boolean darkTheme) {
        this.darkTheme = darkTheme;
    }
    
    public void handleChangeBttn(ActionEvent event){
        oldPass = oldPassword.getText();
        newPass = newPassword.getText();
        
        if(oldPass.equals(currentStudent.getPassword()) && !newPass.isEmpty()){
            passwordStatus.setText("Your old password has been changed");
            passwordStatus.setFill(Color.GREEN);
            currentStudent.setPassword(newPass);
            updatedStudentObj = SettingsController.this.getStudentObject();
            updatedStudentObj.put("password", newPass);
            studentArr.remove(SettingsController.this.getStudentObject());
            studentArr.add(updatedStudentObj);
            try{
                try (FileWriter file = new FileWriter("student.json")) {
                    file.write(studentArr.toJSONString());
                }
            }catch(IOException e) {
                System.out.println("Something went wrong");
            }
        } else if (!oldPass.equals(currentStudent.getPassword()) && !newPass.isEmpty()){
            passwordStatus.setText("You entered the wrong old password");
            passwordStatus.setFill(Color.RED);
        } else if (oldPass.isEmpty() || newPass.isEmpty()) {
            passwordStatus.setText("You did not fill out a field");
            passwordStatus.setFill(Color.RED);
        }
    }
   
}
