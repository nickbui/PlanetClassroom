/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;



import Model.ScienceGame;
import Model.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;



/**
 * FXML Controller class
 *
 * @author Nicholas
 */
public class ScienceGameController implements Initializable {

    @FXML private AnchorPane scienceAnchor;
    @FXML private Text questionText, correctScore, incorrectScore, scoreText;
    @FXML private Button answerBttn1, answerBttn2, answerBttn3, answerBttn4;
    ScienceGame science = new ScienceGame();
    int counter;
    Student currentStudent;
    ArrayList<JSONObject> content = new ArrayList<>();
    int correct;
    int incorrect;
    
    Object ob;
    JSONArray studentArr = new JSONArray();
    org.json.simple.JSONObject studentObj = new org.json.simple.JSONObject();
    org.json.simple.JSONObject updatedStudentObj;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        content = science.getObjectArr();
        Collections.shuffle(content);
        science.getFirstQuestion(content);
        questionText.setText(science.getQuestion());
        
        Iterator<String> iterator = science.getAnswerChoice().iterator();
        while(iterator.hasNext()) {
            answerBttn1.setText(iterator.next());
            answerBttn2.setText(iterator.next());
            answerBttn3.setText(iterator.next());
            answerBttn4.setText(iterator.next());
        }
        
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
    public void handleResetBttn(ActionEvent event) throws IOException{
        FXMLLoader scienceLoader = new FXMLLoader(getClass().getResource("/View/ScienceGameView.fxml"));
        Parent scienceRoot = (Parent) scienceLoader.load();
        ScienceGameController science = scienceLoader.getController();
        science.setCurrentStudent(currentStudent);
        scienceAnchor.getChildren().clear();
        scienceAnchor.getChildren().addAll(scienceRoot);
    }
    
    @FXML
    public void handleAnswerBttn(ActionEvent event) {
            String value = ((Button)event.getSource()).getText();
            if(value.equals(science.getCorrectAnswer())) {
                correct = correct+1;
                correctScore.setText(Integer.toString(correct));
                counter = counter +1;
                System.out.println("That was correct");
                science.getNextQuestion(content, counter);
                questionText.setText(science.getQuestion());
                Iterator<String> nextIterator = science.getAnswerChoice().iterator();
                while(nextIterator.hasNext()) {
                    answerBttn1.setText(nextIterator.next());
                    answerBttn2.setText(nextIterator.next());
                    answerBttn3.setText(nextIterator.next());
                    answerBttn4.setText(nextIterator.next());
                }
            } else {
                incorrect= incorrect+1;
                incorrectScore.setText(Integer.toString(incorrect));
                counter = counter +1;
                System.out.println("Incorrect");
                science.getNextQuestion(content, counter);
                questionText.setText(science.getQuestion());
                Iterator<String> nextIterator = science.getAnswerChoice().iterator();
                
                while(nextIterator.hasNext()) {
                    answerBttn1.setText(nextIterator.next());
                    answerBttn2.setText(nextIterator.next());
                    answerBttn3.setText(nextIterator.next());
                    answerBttn4.setText(nextIterator.next());
                }
        }
        
        if(counter == 10) {
            if(correct >= 7) {
                questionText.setText("You win!");
                answerBttn1.setVisible(false);
                answerBttn2.setVisible(false);
                answerBttn3.setVisible(false);
                answerBttn4.setVisible(false);
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
                
            } else{
                questionText.setText("Sorry. Try Again!");
                answerBttn1.setVisible(false);
                answerBttn2.setVisible(false);
                answerBttn3.setVisible(false);
                answerBttn4.setVisible(false);
            }
        } 
    }
    
     public void setCurrentStudent(Student student){
        this.currentStudent = student;
        scoreText.setText(Integer.toString(currentStudent.getScore()));
    }
     
     public org.json.simple.JSONObject getStudentObject(){
        org.json.simple.JSONObject currentStudentJSON = new org.json.simple.JSONObject();
        for (int i = 0; i<studentArr.size();i++) {
            studentObj = (org.json.simple.JSONObject) studentArr.get(i);
            if(studentObj.get("username").equals(currentStudent.getUsername())) {
                currentStudentJSON = studentObj;
                break;
            }
        }
        return currentStudentJSON;
    } 
}
