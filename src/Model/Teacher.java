/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Nicholas
 */
public class Teacher {
    
    int teacherID;
    String firstName;
    String lastName;
    String username;
    String password;
    ArrayList<Student> studentList;
    
    public Teacher(int teacherID, String firstName, String lastName, String username, String password){
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        studentList = new ArrayList<>();
    }
    
    public void addStudentToList(Student student){
        studentList.add(student);
    }
}
