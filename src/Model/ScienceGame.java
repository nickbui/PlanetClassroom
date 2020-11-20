/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author Nicholas
 */

public final class ScienceGame {
    
   private HttpURLConnection connection;
   private JSONArray incorrectAns;
   private ArrayList<JSONObject> objectArr = new ArrayList<>();
   
   private JSONObject object;
   
   private String question;
   private String correctAnswer;
   private ArrayList<String> answerChoice = new ArrayList<>();
   
   public ScienceGame(){
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try{
        URL url = new URL("https://opentdb.com/api.php?amount=40&category=17&difficulty=easy&type=multiple");
        connection = (HttpURLConnection) url.openConnection();
    
        //Request setup
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        
        int status = connection.getResponseCode();
        
        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(getConnection().getErrorStream()));
            while((line = reader.readLine())!= null) {
                responseContent.append(line);
            }
            reader.close();
        } else {
            reader = new BufferedReader(new InputStreamReader(getConnection().getInputStream()));
             while((line = reader.readLine())!= null) {
                responseContent.append(line);
            }
             reader.close();
        }
        this.parse(responseContent.toString());
        Collections.shuffle(objectArr);
        
        }catch(MalformedURLException e) {
                e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        } finally{
            connection.disconnect();
        }
    }
   
   public void parse(String responseBody) {
        JSONObject object = new JSONObject(responseBody);
        JSONArray array = object.getJSONArray("results");
        JSONObject required = new JSONObject();
        for(int i = 0; i<array.length(); i++) {
           required = array.getJSONObject(i);
            getObjectArr().add(required);
        }
    }
   
    public void getFirstQuestion(ArrayList<JSONObject> objectArr){
        JSONObject firstQuestion = objectArr.get(0);
        question = firstQuestion.getString("question");
        correctAnswer = firstQuestion.getString("correct_answer");
        incorrectAns = firstQuestion.getJSONArray("incorrect_answers");
        for(int i = 0; i< incorrectAns.length(); i++) {
            this.getAnswerChoice().add(incorrectAns.getString(i));
        }
        this.getAnswerChoice().add(correctAnswer);
        Collections.shuffle(this.getAnswerChoice());
    }
    
    public void getNextQuestion(ArrayList<JSONObject> objectArr, int counter) {
        JSONObject nextQuestion = objectArr.get(counter);
        this.setQuestion(nextQuestion.getString("question"));
        this.setCorrectAnswer(nextQuestion.getString("correct_answer"));
        this.setIncorrectAns(nextQuestion.getJSONArray("incorrect_answers"));
        this.answerChoice.clear();
        for(int i = 0; i< this.getIncorrectAns().length(); i++) {
            this.getAnswerChoice().add(this.getIncorrectAns().getString(i));
        }
        this.getAnswerChoice().add(this.getCorrectAnswer());
        Collections.shuffle(this.getAnswerChoice());
    }

    /**
     * @return the connection
     */
    public HttpURLConnection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(HttpURLConnection connection) {
        this.connection = connection;
    }
   
    /**
     * @return the incorrectAns
     */
    public JSONArray getIncorrectAns() {
        return incorrectAns;
    }

    /**
     * @param incorrectAns the incorrectAns to set
     */
    public void setIncorrectAns(JSONArray incorrectAns) {
        this.incorrectAns = incorrectAns;
    }

    /**
     * @return the object
     */
    public JSONObject getObject() {
        return object;
    }

    /**
     * @param object the object to set
     */
    public void setObject(JSONObject object) {
        this.object = object;
    }

    /**
     * @return the objectArr
     */
    public ArrayList<JSONObject> getObjectArr() {
        return objectArr;
    }

    /**
     * @param objectArr the objectArr to set
     */
    public void setObjectArr(ArrayList<JSONObject> objectArr) {
        this.objectArr = objectArr;
    }
    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the correctAnswer
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @param correctAnswer the correctAnswer to set
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return the answerChoice
     */
    public ArrayList<String> getAnswerChoice() {
        return answerChoice;
    }

    /**
     * @param answerChoice the answerChoice to set
     */
    public void setAnswerChoice(ArrayList<String> answerChoice) {
        this.answerChoice = answerChoice;
    }
    
}
