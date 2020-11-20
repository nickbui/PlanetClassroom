/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import static javafx.application.Platform.exit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.json.JSONObject;

/**
 *
 * @author Nicholas
 */
public class GameDialog extends Stage {
    
    private Text textQuestion = new Text();
    
    final ToggleGroup group = new ToggleGroup();
    private boolean correct= false;
    ScienceGame science = new ScienceGame();
    
    ArrayList<JSONObject> content = new ArrayList<>();
    RadioButton answer1 = new RadioButton();
    RadioButton answer2 = new RadioButton();
    RadioButton answer3 = new RadioButton();
    RadioButton answer4 = new RadioButton();
 
    Button submitBtn = new Button("Submit");
    String userAnswer ="";
    
    private Text correctText = new Text();
    
    public GameDialog(){
        content = science.getObjectArr();
        Collections.shuffle(content);
        
        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
               userAnswer = group.getSelectedToggle().getUserData().toString();
            }
            
        });
        
       answer1.setToggleGroup(group);
       answer2.setToggleGroup(group);
       answer3.setToggleGroup(group);
       answer4.setToggleGroup(group);
       
       
       
       submitBtn.setOnAction(event ->{
           if(userAnswer.equals(science.getCorrectAnswer())) {
               correct = true;
               correctText.setText("Correct!");
               correctText.setFill(Color.GREEN);
               answer1.setDisable(true);
               answer2.setDisable(true);
               answer3.setDisable(true);
               answer4.setDisable(true);
           } else{
               correct= false;
               correctText.setText("Sorry Wrong. Correct Answer was " + science.getCorrectAnswer());
               correctText.setFill(Color.RED);
               answer1.setDisable(true);
               answer2.setDisable(true);
               answer3.setDisable(true);
               answer4.setDisable(true);
           }
       });
        
       
        
        VBox vbox = new VBox(10, textQuestion, answer1, answer2, answer3, answer4, submitBtn, correctText);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox);
        setScene(scene);
        initModality(Modality.APPLICATION_MODAL);
       
    }
    
    public void open(){
        science.getFirstQuestion(content);
        textQuestion.setText(science.getQuestion());
        Iterator<String> iterator = science.getAnswerChoice().iterator();
        while(iterator.hasNext()){
            answer1.setUserData(iterator.next());
            answer2.setUserData(iterator.next());
            answer3.setUserData(iterator.next());
            answer4.setUserData(iterator.next());
        }
         answer1.setText(answer1.getUserData().toString());
        answer2.setText(answer2.getUserData().toString());
        answer3.setText(answer3.getUserData().toString());
        answer4.setText(answer4.getUserData().toString());
        correct=false;
        show();
    }
    
    public boolean isCorrect(){
        return correct;
    }
    
}
