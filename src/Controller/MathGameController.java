/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.MathGame24;
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
    @FXML private Text answerBox, statusText, goalText;
    private String operator = "";
    long answer1;
    int counter = 0;
 
    
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
        
    }
    @FXML
    public void handleNumBox(ActionEvent event) {
        goalText.setText("");
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
               box1.setText(String.valueOf(game.calculate(answer1, Long.parseLong(answerBox.getText()), operator)));
               curr = box1.getText();
               answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            } else if((Button) event.getSource() == box2) {
                box2.setText(String.valueOf(game.calculate(answer1, Long.parseLong(answerBox.getText()), operator)));
                curr = box2.getText();
                answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            } else if((Button) event.getSource() == box3) {
                box3.setText(String.valueOf(game.calculate(answer1, Long.parseLong(answerBox.getText()), operator)));
                curr = box3.getText();
                answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            } else if((Button) event.getSource() == box4) {
                box4.setText(String.valueOf(game.calculate(answer1, Long.parseLong(answerBox.getText()), operator)));
                curr = box4.getText();
                answerBox.setText("");
               operator = "";
               counter = counter +1;
               plusBox.setDisable(true);
               minusBox.setDisable(true);
               timesBox.setDisable(true);
               divideBox.setDisable(true);
            }
            
            if (counter == 3 && Long.parseLong(curr) == 24){
                statusText.setText("You win!");
            } else if(counter == 3 && Long.parseLong(curr)!= 24){
                statusText.setText("Try again");
            }
        }
    }
    
    @FXML 
    public void handleOperatorBttn(ActionEvent event) {
        String value = ((Button)event.getSource()).getText();
        operator = value;
        answer1 = Long.parseLong(answerBox.getText());

        if(answer1 == Long.parseLong(box1.getText())){
            box1.setVisible(false);
        } else if(answer1 == Long.parseLong(box2.getText())){
            box2.setVisible(false);
        } else if(answer1 == Long.parseLong(box3.getText())){
           box3.setVisible(false);
        } else if(answer1 == Long.parseLong(box4.getText())){
            box4.setVisible(false);
        } 
    }

    @FXML
    public void handleResetBttn(ActionEvent event) throws IOException{
        Parent mathRoot;
        mathRoot = FXMLLoader.load(getClass().getResource("/View/MathGameView.fxml"));
        mathAnchor.getChildren().clear();
        mathAnchor.getChildren().addAll(mathRoot);
    }
        
    public int randomNumber(){
        int min = 1;
        int max = 26;
        int randomNumber = (int)(Math.random()*(max-min + 1) + min);
        return randomNumber;
    }
    
}
