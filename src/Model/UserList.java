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
public class UserList {
   
    private ArrayList<String> authenticatedUsers;
    private ArrayList<String> authenticatedPassword;
    
    Object ob;
    JSONArray studentArr = new JSONArray();
    JSONObject studentObj = new JSONObject();

    public UserList() {
        this.authenticatedUsers = new ArrayList<>();
        this.authenticatedPassword = new ArrayList<>();
        
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

    /**
     * @return the authenticatedUsers
     */
    public ArrayList<String> getAuthenticatedUsers() {
        return authenticatedUsers;
    }

    /**
     * @return the authenticatedPassword
     */
    public ArrayList<String> getAuthenticatedPassword() {
        return authenticatedPassword;
    }
    
    public boolean authenticate(String user, String password) {
        boolean authentication = false;
        
        for(int i = 0; i < studentArr.size(); i++){
            studentObj = (JSONObject) studentArr.get(i);
            if(user.equals(studentObj.get("username")) && password.equals(studentObj.get("password"))){
                authentication = true;
                break;
            } else{
                authentication = false;
            }
        }
        return authentication;
    }
    
    /**
     * @param authenticatedUsers the authenticatedUsers to set
     */
    public void setAuthenticatedUsers(ArrayList<String> authenticatedUsers) {
        this.authenticatedUsers = authenticatedUsers;
    }

    /**
     * @param authenticatedPassword the authenticatedPassword to set
     */
    public void setAuthenticatedPassword(ArrayList<String> authenticatedPassword) {
        this.authenticatedPassword = authenticatedPassword;
    }
 
}
