/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MathGame24;
import Model.Model;
import Model.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class MathGameController implements Initializable {

    
    /**
     * Initializes the controller class.
     */
    MathGame24 game;
    @FXML private Button box1,box2,box3,box4, plusBox, minusBox, timesBox, divideBox;
    @FXML private AnchorPane mathAnchor;
    @FXML private Text answerBox, statusText, scoreText;
    private String operator = "";
    double answer1 ;
    int counter = 0;
    Model mode;
    Student currentStudent;
    
    Object ob;
    JSONArray studentArr = new JSONArray();
    JSONObject studentObj = new JSONObject();
    JSONObject updatedStudentObj;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        game = new MathGame24();
        
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(randomNumber());
        nums.add(randomNumber());
        nums.add(randomNumber());
        nums.add(randomNumber());
        
        
        
        while(game.judgePoint24(nums) == false) {
            nums.set(0, randomNumber());
            nums.set(1, randomNumber());
            nums.set(2, randomNumber());
            nums.set(3, randomNumber());
        }
        if(game.judgePoint24(nums) == true){
            int num1 = nums.get(0);
            int num2 = nums.get(1);
            int num3 = nums.get(2);
            int num4 = nums.get(3);
   
            box1.setText(Integer.toString(num1));
            box2.setText(Integer.toString(num2));
            box3.setText(Integer.toString(num3));
            box4.setText(Integer.toString(num4));
        }
        plusBox.setDisable(true);
        minusBox.setDisable(true);
        timesBox.setDisable(true);
        divideBox.setDisable(true);
        
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
    
    @FXML
    public void handleNumBox(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        String curr = "";
        plusBox.setDisable(false);
        minusBox.setDisable(false);
        timesBox.setDisable(false);
        divideBox.setDisable(false);
        
        if( operator.isEmpty()){
            answerBox.setText(value);
            
            plusBox.setDisable(false);
            minusBox.setDisable(false);
            timesBox.setDisable(false);
            divideBox.setDisable(false);
        }
        else if (!operator.isEmpty()){
            answerBox.setText(value);
            plusBox.setDisable(false);
            minusBox.setDisable(false);
            timesBox.setDisable(false);
            divideBox.setDisable(false);
            
            if((Button) event.getSource() == box1) {
               box1.setText(String.valueOf(game.calculate(answer1, this.roundDouble(answerBox.getText()), operator)));
               curr = box1.getText();
               answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            } else if((Button) event.getSource() == box2) {
                box2.setText(String.valueOf(game.calculate(answer1, this.roundDouble(answerBox.getText()), operator)));
                curr = box2.getText();
                answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            } else if((Button) event.getSource() == box3) {
                box3.setText(String.valueOf(game.calculate(answer1, this.roundDouble(answerBox.getText()), operator)));
                curr = box3.getText();
                answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            } else if((Button) event.getSource() == box4) {
                box4.setText(String.valueOf(game.calculate(answer1,this.roundDouble(answerBox.getText()), operator)));
                curr = box4.getText();
                answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            }
            
            if (counter == 3 && this.roundDouble(curr) == 24){
                statusText.setText("You win!");
                int score = currentStudent.getScore() +1;
                currentStudent.setScore(score);
                scoreText.setText(Integer.toString(score));
                updatedStudentObj = this.getStudentObject();
                updatedStudentObj.put("score", score);
                studentArr.remove(this.getStudentObject());
                studentArr.add(updatedStudentObj);
        
                try{
                FileWriter file = new FileWriter("student.json");
                file.write(studentArr.toJSONString());
                file.close();
                }catch(Exception e) {
                    System.out.println("Something went wrong");
                }   
                
            } else if(counter == 3 && this.roundDouble(curr)!= 24){
                statusText.setText("Try again");
            }
        }
    }
    
    @FXML 
    public void handleOperatorBttn(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        operator = value;
        
        answer1 = this.roundDouble(answerBox.getText());

        if(answer1 == this.roundDouble(box1.getText())){
            box1.setVisible(false);
        } else if(answer1 == this.roundDouble(box2.getText())){
            box2.setVisible(false);
        } else if(answer1 == this.roundDouble(box3.getText())){
           box3.setVisible(false);
        } else if(answer1 == this.roundDouble(box4.getText())){
            box4.setVisible(false);
        } 
    }

    @FXML
    public void handleResetBttn(ActionEvent event) throws IOException{
        FXMLLoader mathLoader = new FXMLLoader(getClass().getResource("/View/MathGameView.fxml"));
        Parent mathRoot = (Parent) mathLoader.load();
        MathGameController math = mathLoader.getController();
        math.setCurrentStudent(currentStudent);
        mathAnchor.getChildren().clear();
        mathAnchor.getChildren().addAll(mathRoot);
    }
        
    public int randomNumber(){
        int min = 1;
        int max = 26;
        int randomNumber = (int)(Math.random()*(max-min + 1) + min);
        return randomNumber;
    }
    
    public double roundDouble(String text){
        double number = Double.parseDouble(text);
        double roundedNum = Math.round(number * 100.0) / 100.0;
        return roundedNum;
    }
    
     public void setCurrentStudent(Student student){
        this.currentStudent = student;
        scoreText.setText(Integer.toString(currentStudent.getScore()));
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
    
}
