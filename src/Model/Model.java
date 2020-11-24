/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.FileReader;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Nicholas
 */
public final class Model {
    

    private UserList uL;
    private ArrayList<Student> studentList = new ArrayList<>();
    private JSONObject userObject;
    private JSONArray studentArr = new JSONArray();
    
    public Model() {
        uL = new UserList();
        this.userdataFromJSON();
    }


    /**
     * @return the uL
     */
    public UserList getuL() {
        return uL;
    }
    
     public void addUserToList(String user) {
        getuL().getAuthenticatedUsers().add(user);
    }
    
    public void addPassToList(String pass) {
        getuL().getAuthenticatedPassword().add(pass);
    }

    /**
     * @param uL the uL to set
     */
    public void setuL(UserList uL) {
        this.uL = uL;
    }
  
    /**
     * @return the studentList
     */
    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    /**
     * @param studentList the studentList to set
     */
    public void setStudentList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }
    
     public void userdataFromJSON(){
        Object ob;
        JSONParser jp = new JSONParser();
            try{
                FileReader reader = new FileReader("student.json");
                ob = jp.parse(reader);
                studentArr = (JSONArray)ob;
                reader.close();
            } catch(Exception e) {
                System.out.println("Something went wrong");
        }
        for (int i= 0; i<getStudentArr().size();i++) {
            setUserObject((JSONObject)getStudentArr().get(i));
            int studentIDJSON = Integer.parseInt(getUserObject().get("student id").toString());
            String firstNameJSON = getUserObject().get("first name").toString();
            String lastNameJSON = getUserObject().get("last name").toString();
            String userJSON = getUserObject().get("username").toString();
            String passwordJSON = getUserObject().get("password").toString();
            int scoreJSON = Integer.parseInt(getUserObject().get("score").toString());
            int stickerIDJSON = Integer.parseInt(getUserObject().get("sticker id").toString());
            boolean darkTheme = (Boolean) getUserObject().get("dark theme");
            Student JSONStudent = new Student(studentIDJSON, firstNameJSON, lastNameJSON, userJSON, passwordJSON,scoreJSON,stickerIDJSON, darkTheme);
            studentList.add(JSONStudent);
        }
    }

    /**
     * @return the userObject
     */
    public JSONObject getUserObject() {
        return userObject;
    }

    /**
     * @param userObject the userObject to set
     */
    public void setUserObject(JSONObject userObject) {
        this.userObject = userObject;
    }

    /**
     * @return the studentArr
     */
    public JSONArray getStudentArr() {
        return studentArr;
    }

    /**
     * @param studentArr the studentArr to set
     */
    public void setStudentArr(JSONArray studentArr) {
        this.studentArr = studentArr;
    }
  
}