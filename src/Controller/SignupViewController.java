/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Model;
import Model.UserList;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class SignupViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML private TextField firstNameText, lastNameText, usernameText, passwordText;
    
   UserList ul = new UserList();
   Model model = new Model();
   
   Object ob;
   JSONArray studentArr = new JSONArray();
   JSONObject studentObj = new JSONObject();
   
   @FXML private Text statusText;
    
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

    @FXML public void handleJoinBttn(ActionEvent event) throws IOException{
        
        int studentID = this.getStudentNumber(studentArr.size());
        JSONObject studentData = new JSONObject();
        
        studentData.put("student id",studentID );
        studentData.put("first name", firstNameText.getText());
        studentData.put("last name", lastNameText.getText());
        studentData.put("username", usernameText.getText());
        studentData.put("password", passwordText.getText());
        studentData.put("score", 0);
        studentData.put("sticker id", 1);
        studentData.put("dark theme", false);
        
        for(int i = 0; i<studentArr.size();i++){
            JSONObject username = (JSONObject)studentArr.get(i);
            
            if(studentData.get("username").equals(username.get("username"))) {
                statusText.setText("You already have an account.");
                break;
            } else if (i ==studentArr.size()-1) {
                statusText.setText("Successful!");
                studentData.put("student id", studentID);
                studentData.put("first name", firstNameText.getText());
                studentData.put("last name", lastNameText.getText());
                studentData.put("username", usernameText.getText());
                studentData.put("password", passwordText.getText());
                studentData.put("score", 0);
                studentData.put("sticker id", 1);
                studentArr.add(studentData);
                try{
                    FileWriter file = new FileWriter("student.json");
                    file.write(studentArr.toString());
                    file.close();
                }catch(Exception e) {
                    System.out.println("Something went wrong");
                }
                break;
            }
        }
    }
    
    @FXML public void handleGoBackBttn(ActionEvent event) throws IOException{
        Parent root =FXMLLoader.load(getClass().getResource("/View/LoginView.fxml"));
        Scene primaryScence = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Welcome");
        stage.setScene(primaryScence);
        stage.show();
        ((Node)(event.getSource())).getScene().getWindow().hide();
    } 

   public int getStudentNumber(int n) {
       n++;
       return n;
   }

}
